package com.coordominium.events.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.coordominium.invitations.entity.Invitation;

//@Entity
//@DiscriminatorValue("EVENT")
public class EventInvitation extends Invitation {

	private Event event;
}
