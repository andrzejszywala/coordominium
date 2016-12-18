package com.coordominium.device.boundary;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.coordominium.UserId;
import com.coordominium.coordinates.entity.Point;
import com.coordominium.device.entity.Device;
import com.coordominium.users.entity.User;

@Stateless
@Path("/devices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DevicesResource {

	@PersistenceContext
	EntityManager em;
	@Context 
	HttpServletRequest servletRequest;

	private static final Logger LOG = Logger.getLogger(DevicesResource.class.getName());

	@GET
	@Path("/{device}/coordinates")
	public Collection<Point> coordinates(@PathParam("device") String device) {
		return em.createNamedQuery("Coordinate.byDevice", Point.class)
				.setParameter("userId", UserId.get(servletRequest))
				.setParameter("device", device)
				.getResultList();
	}
	
	@GET
	public JsonArray devices() {
		return em.createNamedQuery("Device.userDevices", Device.class)
				.setParameter("userId", UserId.get(servletRequest))
				.getResultList()
				.stream().map(d -> {
					return Json.createObjectBuilder()
							.add("id", d.getId())
							.add("name", d.getName())
							.add("description", d.getDescription() == null ? "" : d.getDescription());
				}).collect(Json::createArrayBuilder, JsonArrayBuilder::add, JsonArrayBuilder::add).build();
	}
	
	@POST
	public Response save(JsonObject json) {
		Device device = new Device();
		device.setName(json.getString("name"));
		device.setDescription(json.getString("description", null));
		device.setUser(em.getReference(User.class, UserId.get(servletRequest)));
		try {
			em.persist(device);			
			em.flush();
			return Response.created(new URI("" + device.getId())).build();
		} catch (PersistenceException ex) {
			LOG.log(Level.INFO, ex.getLocalizedMessage(), ex);
			return Response.status(Status.CONFLICT).build();
		} catch (URISyntaxException ex) {
			LOG.log(Level.INFO, ex.getLocalizedMessage(), ex);
			return Response.serverError().build();
		}		
	}
	
	@DELETE
	@Path("/{deviceId:[0-9][0-9]*}")
	public Response delete(@PathParam("deviceId") long deviceId) {
		Device device = em.find(Device.class, deviceId);
		if (device == null) {
			return Response.ok().build();
		} else if (!UserId.get(servletRequest).equals(device.getUser().getId())) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		em.createNamedQuery("Coordinate.deleteByDevice")
			.setParameter("deviceId", deviceId)
			.executeUpdate();
		em.createNamedQuery("Device.deleteUserDevice")
			.setParameter("deviceId", deviceId)
			.executeUpdate();
		return Response.ok().build();
	}
}
