package com.coordominium.coordinates.boundary;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.coordominium.UserId;
import com.coordominium.coordinates.control.Coordinates;
import com.coordominium.coordinates.entity.Coordinate;
import com.coordominium.coordinates.entity.CoordinateQuery;
import com.coordominium.coordinates.entity.Point;
import com.coordominium.coordinates.entity.Provider;
import com.coordominium.dates.DatesUtil;
import com.coordominium.device.entity.Device;
import com.coordominium.users.entity.User;

@Stateless
@Path("/coordinates")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoordinatesResource {

	@PersistenceContext
	EntityManager em;
	@Context 
	HttpServletRequest servletRequest;
	@Inject	
	Coordinates coordinates;
	

	@GET
	@Path("/{deviceId:[0-9][0-9]*}")
	public Collection<Point> coordinates(@PathParam("deviceId") long deviceId) {
		return em.createNamedQuery("Coordinate.byDevice", Point.class)
				.getResultList();
	}

	@POST
	public Response save(JsonObject jsonObject) {
		Device entity = null;
		try {
			entity = em.createNamedQuery("Device.userDevice", Device.class)
				.setParameter("userId", UserId.get(servletRequest))
				.setParameter("device", jsonObject.getString("device"))
				.getSingleResult();
		} catch (NoResultException ex) {
			entity = new Device(jsonObject.getString("device"), new User(UserId.get(servletRequest)));
			em.persist(entity);
		}
		Coordinate coordinate = null;
		for (JsonValue jsonValue : jsonObject.getJsonArray("coordinates")) {
			JsonObject coordinateJson = (JsonObject) jsonValue;
			coordinate = new Coordinate();
			coordinate.setDevice(entity);
			coordinate.setTime(DatesUtil.asCalendar((coordinateJson.getString("time"))));
			coordinate.setLatitude(coordinateJson.getJsonNumber("latitude").bigDecimalValue());
			coordinate.setLongitude(coordinateJson.getJsonNumber("longitude").bigDecimalValue());
			coordinate.setProvider(Provider.valueOf(coordinateJson.getString("provider").toUpperCase()));
			em.persist(coordinate);
		}
		return Response.ok().build();
	}
	

	@DELETE
	public void delete() {
		Device entity = em.getReference(Device.class, 1L);
		for (Coordinate coordinate : entity.getCoordinates()) {
			em.remove(coordinate);
		}
	}
	
	@POST
	@Path("/search")
	public Collection<Point> search(CoordinateQuery query) {
		return coordinates.get(query);
	}
}
