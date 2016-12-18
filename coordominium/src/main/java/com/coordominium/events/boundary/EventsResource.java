package com.coordominium.events.boundary;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.coordominium.UserId;
import com.coordominium.dates.DatesUtil;
import com.coordominium.events.entity.Event;
import com.coordominium.events.entity.EventType;
import com.coordominium.users.entity.User;

@Stateless
@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventsResource {

	@PersistenceContext
	EntityManager em;
	@Context 
	HttpServletRequest servletRequest;
	
	@GET
	public JsonArray events() {
		Collection<Long> participateEvents = new HashSet<>(em.createNamedQuery("Event.participateEvents", Long.class)
			.setParameter("startTime", Calendar.getInstance())
			.setParameter("types", Arrays.asList(EventType.PUBLIC))
			.setParameter("userId", UserId.get(servletRequest))
			.getResultList());
		
		return em.createNamedQuery("Event.eventsByTimeAndTypes", Event.class)
				.setParameter("startTime", Calendar.getInstance())
				.setParameter("types", Arrays.asList(EventType.PUBLIC))
				.getResultList()
				.stream().map(e -> {
					return Json.createObjectBuilder()
							.add("id", e.getId())
							.add("name", e.getName())
							.add("description", e.getDescription() == null ? "" : e.getDescription())
							.add("startTime", e.getStartTime() == null ? "" : DatesUtil.dateTimeString(e.getStartTime()))
							.add("endTime", e.getEndTime() == null ? "" : DatesUtil.dateTimeString(e.getEndTime()))
							.add("participate", participateEvents.contains(e.getId()));
				}).collect(Json::createArrayBuilder, JsonArrayBuilder::add, JsonArrayBuilder::add).build();
	}
	
	@POST
	public void save(JsonObject jsonObject) {
		Event event = new Event();
		event.setOwner(em.getReference(User.class, UserId.get(servletRequest)));
		event.setName(jsonObject.getString("name"));
		event.setDescription(jsonObject.getString("description"));
		event.setType(EventType.valueOf(jsonObject.getString("type")));
		event.setStartTime(jsonObject.containsKey("startTime") ? DatesUtil.asCalendar(jsonObject.getString("startTime")) : null);
		event.setEndTime(jsonObject.containsKey("endTime") ? DatesUtil.asCalendar(jsonObject.getString("endTime")) : null);
		
		event.setLatitude(jsonObject.containsKey("latitude") ? new BigDecimal(jsonObject.getString("latitude")) : null);
		event.setLongitude(jsonObject.containsKey("longitude") ? new BigDecimal(jsonObject.getString("longitude")) : null);
		event.setRadius(jsonObject.containsKey("radious") ? jsonObject.getInt("radious") : null);
		
		em.persist(event);
	}
}
