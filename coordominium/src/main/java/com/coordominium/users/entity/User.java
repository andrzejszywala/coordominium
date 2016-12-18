package com.coordominium.users.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.coordominium.device.entity.Device;

@Entity
@Table(name = "USER_ENTITY")
// @SecondaryTable(name="USER_ENTITY",
// pkJoinColumns={@PrimaryKeyJoinColumn(name="ID")})
public class User {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "EMAIL")
	private String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Device> devices;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Device> sharedDevices;

	// @JoinTable(name = "friends",
	// joinColumns = { @JoinColumn(name = "friend1", referencedColumnName =
	// "id", nullable = false)},
	// inverseJoinColumns = { @JoinColumn(name = "friend2", referencedColumnName
	// = "id", nullable = false)})
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<User> friends;

	// @ManyToMany(fetch = FetchType.LAZY)
	// private Set<NotificationType> subscriptions;

	// @OneToMany(fetch = FetchType.LAZY)
	// private Set<FriendInvitation> invitations;

	public User() {
	}

	public User(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public Set<Device> getSharedDevices() {
		return sharedDevices;
	}

	public void setSharedDevices(Set<Device> sharedDevices) {
		this.sharedDevices = sharedDevices;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}
	//
	// public Set<NotificationType> getSubscriptions() {
	// return subscriptions;
	// }
	//
	// public void setSubscriptions(Set<NotificationType> subscriptions) {
	// this.subscriptions = subscriptions;
	// }
	//
	// public Set<FriendInvitation> getInvitations() {
	// return invitations;
	// }
	//
	// public void setInvitations(Set<FriendInvitation> invitations) {
	// this.invitations = invitations;
	// }

}
