package com.coordominium.coordinates.entity;

import java.util.Calendar;
import java.util.Collection;

import com.coordominium.dates.DatesUtil;

public class CoordinateQuery {

	private Collection<Long> devices;
	private Boolean gps;
	private Boolean network;
	private String startDate;
	private String endDate;

	public Calendar start() {
		return DatesUtil.asCalendar(startDate);
	}

	public Calendar end() {
		return DatesUtil.asCalendar(endDate);
	}

	public Collection<Long> getDevices() {
		return devices;
	}

	public void setDevices(Collection<Long> devices) {
		this.devices = devices;
	}

	public Boolean getGps() {
		return gps;
	}

	public void setGps(Boolean gps) {
		this.gps = gps;
	}

	public Boolean getNetwork() {
		return network;
	}

	public void setNetwork(Boolean network) {
		this.network = network;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
