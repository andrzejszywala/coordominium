package com.coordominium.users.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.coordominium.invitations.entity.Invitation;

//@Entity
//@DiscriminatorValue("FRIEND")
public class FriendInvitation extends Invitation {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User inviting;

	public User getInviting() {
		return inviting;
	}

	public void setInviting(User inviting) {
		this.inviting = inviting;
	}
	
}
