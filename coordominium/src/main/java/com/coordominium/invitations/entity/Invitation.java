package com.coordominium.invitations.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.coordominium.users.entity.User;

//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "TYPE")
//@Table(name = "INVITATION")
public abstract class Invitation {

	@Id
	private long id;

	private User user;
	
	private ConfirmationType confirmationType;
	
	
}
