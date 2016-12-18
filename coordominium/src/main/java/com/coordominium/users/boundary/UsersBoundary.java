package com.coordominium.users.boundary;

import java.net.URI;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.coordominium.users.entity.ProfileInfo;
import com.coordominium.users.entity.User;

@Stateless
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersBoundary {

	@PersistenceContext
	EntityManager em;
	
	@POST
	public Response create(ProfileInfo newProfile) {
		em.persist(new User());
		return Response.created(URI.create(newProfile.getLogin())).build();
	}
}
