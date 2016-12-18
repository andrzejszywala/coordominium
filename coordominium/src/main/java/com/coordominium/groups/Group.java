package com.coordominium.groups;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.coordominium.events.entity.Event;
import com.coordominium.users.entity.User;

//@Entity
public class Group {

	@Id
	private Long id;
	
	private String name;
	
	private String description;
	
//	private String email;
//	
//	private User owner;
//	
//	@Enumerated(EnumType.STRING)
//	private Privacy privacy;
//	
//	private Set<User> members;
//	
//	private Set<Event> events;
//	
//	private Set<User> admins;
}
