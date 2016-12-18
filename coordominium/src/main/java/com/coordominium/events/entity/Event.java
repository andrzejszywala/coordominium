package com.coordominium.events.entity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.coordominium.device.entity.Device;
import com.coordominium.users.entity.User;

@NamedQueries({
	@NamedQuery(name = "Event.eventsByTimeAndTypes",
			query = "SELECT e FROM Event e WHERE e.startTime >= :startTime AND e.type IN (:types)"),
	@NamedQuery(name = "Event.participateEvents",
			query = " SELECT e.id "
					+ " FROM Event e "
					+ " JOIN e.attending a "
					+ "WHERE e.startTime >= :startTime "
					+ "  AND e.type IN (:types) "
					+ "  AND a.user.id = :userId ")
})
@Entity
@Table(name = "CRD_EVENT")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "NAME", length = 30)
	private String name;
	
	@Column(name = "DESCRIPTION", length = 1000)
	private String description;
	
	@Column(name = "START_TIME")
	private Calendar startTime;
	
	@Column(name = "END_TIME")
	private Calendar endTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User owner;
	
//	@ManyToOne
//	private Group group;
	
	@Column(name = "LATITUDE", precision = 16, scale = 14)
	private BigDecimal latitude;
	
	@Column(name = "LONGITUDE", precision = 16, scale = 14)
	private BigDecimal longitude;
	
	@Column(name = "RADIUS", length = 4)
	private Integer radius;
	
	@Enumerated(EnumType.STRING)
	private EventType type;
	
	@ManyToMany
	private Set<Device> attending;
	
//	@ManyToMany
//	private Set<User> admins;

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

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

//	public Group getGroup() {
//		return group;
//	}
//
//	public void setGroup(Group group) {
//		this.group = group;
//	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public Set<Device> getAttending() {
		return attending;
	}

	public void setAttending(Set<Device> attending) {
		this.attending = attending;
	}

//	public Set<User> getAdmins() {
//		return admins;
//	}
//
//	public void setAdmins(Set<User> admins) {
//		this.admins = admins;
//	}
	
}
