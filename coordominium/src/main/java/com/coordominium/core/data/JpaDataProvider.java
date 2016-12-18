package com.coordominium.core.data;

import java.util.Calendar;
import java.util.Map.Entry;

import javax.persistence.Query;

public abstract class JpaDataProvider<R, Q> extends BaseDataProvider<R, Q> {

	@Override
	protected Query buildQuery() {
		final Query qry = em.createQuery(query.toString());
        for ( final Entry<Object, Object> entry : whereParams.entrySet()) {
        	qry.setParameter(entry.getKey().toString(), entry.getValue());
        }   
        return qry;
	}
	
	protected void compareDates(String entity, String field, Calendar start, Calendar end) {
		if (present(start) && present(end)) {
			query.append(" ").append(entity).append(".").append(field).append(" BETWEEN ").append(":start").append(field).append(" AND ").append(":end").append(field).append(" ");
			whereParams.put("start" + field, start);
			whereParams.put("end" + field, end);
		} else if (present(start)) {
			query.append(" ").append(entity).append(".").append(field).append(" >= ").append(":start").append(field).append(" ");
			whereParams.put("start" + field, start);
		} else if (present(end)) {
			query.append(" ").append(entity).append(".").append(field).append(" <= ").append(":end").append(field).append(" ");
			whereParams.put("end" + field, end);
		}
	}
}
