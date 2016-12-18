package com.coordominium.coordinates.entity;

import java.math.BigDecimal;
import java.util.Calendar;

import com.coordominium.dates.DatesUtil;

public class Point {

	private Long deviceId;
	private Provider provider;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private String time;

	public Point() {
	}
	
	public Point(Long deviceId, Provider provider, BigDecimal latitude, BigDecimal longitude, Calendar time) {
		super();
		this.deviceId = deviceId;
		this.provider = provider;
		this.latitude = latitude;
		this.longitude = longitude;
		this.time = DatesUtil.dateTimeString(time);
	}

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
}
