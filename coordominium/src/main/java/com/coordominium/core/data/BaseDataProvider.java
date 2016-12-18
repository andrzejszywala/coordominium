package com.coordominium.core.data;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class BaseDataProvider<R, P> implements DataProvider<R, P> {

	@Inject
	protected EntityManager em;
	protected Map<Object, Object> whereParams = new HashMap<>();
	protected StringBuilder query = new StringBuilder();
	

	@SuppressWarnings("unchecked")
	public Collection<R> get(P parameters) {
        generateQuery(parameters);
        return buildQuery().getResultList();
	}
	
	protected abstract void generateQuery(P parameters);
	
	protected abstract Query buildQuery();
	
	protected boolean present(Object parameter) {
		return parameter != null;
	}
}
