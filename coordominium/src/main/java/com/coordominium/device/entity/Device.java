package com.coordominium.device.entity;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.coordominium.coordinates.entity.Coordinate;
import com.coordominium.users.entity.User;

@NamedQueries({
	@NamedQuery(name = "Device.userDevices",
			query = "SELECT d FROM Device d WHERE d.user.id = :userId"),
	@NamedQuery(name = "Device.userDevice",
		query = "SELECT d FROM Device d WHERE d.user.id = :userId AND d.name = :device "),
	@NamedQuery(name = "Device.deleteUserDevice",
			query = "DELETE FROM Device d WHERE d.id = :deviceId")
})
@Entity
@Table(name = "CRD_DEVICE", uniqueConstraints={@UniqueConstraint(columnNames={"USER_ID", "NAME"})})
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", length = 20, nullable = false)
	private String name;

	@Column(name = "DESCRIPTION", length = 200, nullable = true)
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<User> sharing;

	@OneToMany(mappedBy = "device", fetch = FetchType.LAZY)
	@OrderBy("time")
	private Set<Coordinate> coordinates;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "device")
	private DeviceNotification deviceNotification;
	
	public Device() {
	}
	
	public Device(Long id) {
		super();
		this.id = id;
	}

	public Device(String name, User user) {
		super();
		this.name = name;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Coordinate> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Set<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<User> getSharing() {
		return sharing;
	}

	public void setSharing(Set<User> sharing) {
		this.sharing = sharing;
	}

	public DeviceNotification getDeviceNotification() {
		return deviceNotification;
	}

	public void setDeviceNotification(DeviceNotification deviceNotification) {
		this.deviceNotification = deviceNotification;
	}
	
}
