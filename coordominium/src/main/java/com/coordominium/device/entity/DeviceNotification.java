package com.coordominium.device.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CRD_DEVICE_NOTIF")
public class DeviceNotification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Temporal(TemporalType.TIME)
	private Date start;
	
	@Temporal(TemporalType.TIME)
	private Date end;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastNotification;
	
	@OneToOne
	@JoinColumn(name = "DEVICE_ID")
	private Device device;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Calendar getLastNotification() {
		return lastNotification;
	}

	public void setLastNotification(Calendar lastNotification) {
		this.lastNotification = lastNotification;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
	
}
