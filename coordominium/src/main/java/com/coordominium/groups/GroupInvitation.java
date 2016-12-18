package com.coordominium.groups;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.coordominium.invitations.entity.Invitation;

//@Entity
//@DiscriminatorValue("GROUP")
public class GroupInvitation extends Invitation {

	@ManyToOne
	private Group group;
}
