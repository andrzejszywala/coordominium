package com.coordominium.coordinates.control;

import java.util.Calendar;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.coordominium.coordinates.entity.CoordinateQuery;
import com.coordominium.coordinates.entity.Point;
import com.coordominium.coordinates.entity.Provider;
import com.coordominium.core.data.JpaDataProvider;

@Named
@RequestScoped
public class Coordinates extends JpaDataProvider<Point, CoordinateQuery> {

	@Override
	protected void generateQuery(CoordinateQuery parameters) {
		query.append(" SELECT new com.coordominium.coordinates.entity.Point(c.device.id, c.provider, c.latitude, c.longitude, c.time)")
			.append("    FROM Coordinate c ")
			.append("   WHERE 1=1 ");
		
		Calendar start = parameters.start();
		Calendar end = parameters.end();
		if (present(start) || present(end)) {
			query.append(" AND ");
			compareDates("c", "time", start, end);
		}
		if (present(parameters.getDevices())) {
			query.append(" AND c.device.id IN (:devices)");
			whereParams.put("devices", parameters.getDevices());
		}
		if (Boolean.TRUE.equals(parameters.getGps()) && Boolean.TRUE.equals(parameters.getNetwork())) {
			query.append(" AND (c.provider = :providerGps OR c.provider = :providerNetwork) ");
			whereParams.put("providerGps", Provider.GPS);
			whereParams.put("providerNetwork", Provider.NETWORK);
		} else if (Boolean.TRUE.equals(parameters.getGps())) {
			query.append(" AND c.provider = :providerGps ");
			whereParams.put("providerGps", Provider.GPS);
		} else if (Boolean.TRUE.equals(parameters.getNetwork())) {
			query.append(" AND c.provider = :providerNetwork ");
			whereParams.put("providerNetwork", Provider.NETWORK);
		} 
		query.append(" ORDER BY c.time");
	}
	
}
