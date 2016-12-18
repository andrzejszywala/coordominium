package com.coordominium.coordinates.entity;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.coordominium.device.entity.Device;


@NamedQueries({
	@NamedQuery(name = "Coordinate.byDevice",
			query = " SELECT NEW com.coordominium.coordinates.entity.Point(c.device.id, c.provider, c.latitude, c.longitude, c.time) "
					+ " FROM Coordinate c "
					+ "ORDER BY c.time"),
	@NamedQuery(name = "Coordinate.deleteByDevice",
			query = "DELETE FROM Coordinate c WHERE c.device.id = :deviceId")
})
@Entity
@Table(name = "CRD_COORDINATE", 
		indexes = {@Index(name = "coordinate_device_idx", columnList = "DEVICE_ID", unique = false) }, 
		uniqueConstraints = {@UniqueConstraint(columnNames = { "DEVICE_ID", "TIME" })})
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(precision = 16, scale = 14)
	private BigDecimal latitude;
	
	@Column(precision = 16, scale = 14)
	private BigDecimal longitude;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar time;
	
	@Enumerated(EnumType.STRING)
	private Provider provider;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEVICE_ID")
	private Device device;

	@Transient
	private String dateString;

	public Coordinate() {
	}
	
	public Coordinate(BigDecimal latitude, BigDecimal longitude, Calendar time, Device device) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.time = time;
		this.device = device;
	}
	
	public Coordinate(BigDecimal latitude, BigDecimal longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

}
