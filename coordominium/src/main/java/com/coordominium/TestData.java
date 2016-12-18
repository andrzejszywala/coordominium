package com.coordominium;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.coordominium.coordinates.entity.Coordinate;
import com.coordominium.coordinates.entity.Provider;
import com.coordominium.dates.DatesUtil;
import com.coordominium.device.entity.Device;
import com.coordominium.device.entity.DeviceNotification;
import com.coordominium.events.entity.Event;
import com.coordominium.events.entity.EventType;
import com.coordominium.users.entity.User;


@Singleton
@Startup
public class TestData {

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	private void init() {
//		User entity = new User("edf09f18-347d-4336-bc6a-9224d31a5e09");
//		
//		em.persist(entity);
//		first();
//		second();
//		events();
//		samsung();
	}

	private void samsung() {
		Device device = new Device();
		device.setUser(em.getReference(User.class, "edf09f18-347d-4336-bc6a-9224d31a5e09"));
		device.setName("samsung");
		device.setDescription("samsung galaxy s2 plus");
		em.persist(device);
	}

	private void events() {
		Event event = new Event();
		event.setDescription("fklasdj;fląśłf f adfad ofi asodfć");
		event.setEndTime(DatesUtil.END);
		event.setName("Wypad na pizzę");
		event.setOwner(em.getReference(User.class, "edf09f18-347d-4336-bc6a-9224d31a5e09"));
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		event.setStartTime(cal);
		event.setType(EventType.PUBLIC);
		em.persist(event);
	}

	private void first() {
		Device device = new Device();
		device.setUser(em.getReference(User.class, "edf09f18-347d-4336-bc6a-9224d31a5e09"));
		device.setName("phone");
		
		DeviceNotification notif = new DeviceNotification();
		
		notif.setDevice(device);
		device.setDeviceNotification(notif);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(0, 0, 0, 0, 0));
		notif.setLastNotification(calendar);
		notif.setStart(new Date(0, 0, 0, 20, 0));
		notif.setEnd(new Date(0, 0, 0, 6, 0));

		em.persist(device);

		Coordinate coordinate = new Coordinate();
		coordinate.setDevice(device);
		coordinate.setLatitude(new BigDecimal("50.149400"));
		coordinate.setLongitude(new BigDecimal("18.84028"));
		coordinate.setTime(new GregorianCalendar(2016, 7, 01, 12, 0));
		coordinate.setProvider(Provider.GPS);
		em.persist(coordinate);

		coordinate = new Coordinate();
		coordinate.setDevice(device);
		coordinate.setLatitude(new BigDecimal("50.149540"));
		coordinate.setLongitude(new BigDecimal("18.841799"));
		coordinate.setTime(new GregorianCalendar(2016, 7, 01, 12, 1));
		coordinate.setProvider(Provider.GPS);
		em.persist(coordinate);

		coordinate = new Coordinate();
		coordinate.setDevice(device);
		coordinate.setLatitude(new BigDecimal("50.149678"));
		coordinate.setLongitude(new BigDecimal("18.843838"));
		coordinate.setTime(new GregorianCalendar(2016, 7, 01, 12, 2));
		coordinate.setProvider(Provider.GPS);
		em.persist(coordinate);

		coordinate = new Coordinate();
		coordinate.setDevice(device);
		coordinate.setLatitude(new BigDecimal("50.148949"));
		coordinate.setLongitude(new BigDecimal("18.846434"));
		coordinate.setTime(new GregorianCalendar(2016, 7, 26, 22, 44));
		coordinate.setProvider(Provider.GPS);
		em.persist(coordinate);

		coordinate = new Coordinate();
		coordinate.setDevice(device);
		coordinate.setLatitude(new BigDecimal("50.149691"));
		coordinate.setLongitude(new BigDecimal("18.847100"));
		coordinate.setTime(new GregorianCalendar(2016, 7, 26, 23, 44));
		coordinate.setProvider(Provider.GPS);
		em.persist(coordinate);
	}

	private void second() {
		Device device = new Device();
		device.setUser(em.getReference(User.class, "edf09f18-347d-4336-bc6a-9224d31a5e09"));
		device.setName("phone1");
		em.persist(device);

		Coordinate coordinate = new Coordinate();
		coordinate.setDevice(device);
		coordinate.setLatitude(new BigDecimal("50.152180"));
		coordinate.setLongitude(new BigDecimal("18.842229"));
		coordinate.setTime(new GregorianCalendar(2016, 7, 01, 12, 0));
		coordinate.setProvider(Provider.GPS);
		em.persist(coordinate);

		coordinate = new Coordinate();
		coordinate.setDevice(device);
		coordinate.setLatitude(new BigDecimal("50.152056"));
		coordinate.setLongitude(new BigDecimal("18.842958"));
		coordinate.setTime(new GregorianCalendar(2016, 7, 01, 12, 1));
		coordinate.setProvider(Provider.GPS);
		em.persist(coordinate);

		coordinate = new Coordinate();
		coordinate.setDevice(device);
		coordinate.setLatitude(new BigDecimal("50.151217"));
		coordinate.setLongitude(new BigDecimal("18.836993"));
		coordinate.setTime(new GregorianCalendar(2016, 7, 01, 12, 2));
		coordinate.setProvider(Provider.NETWORK);
		em.persist(coordinate);

		coordinate = new Coordinate();
		coordinate.setDevice(device);
		coordinate.setLatitude(new BigDecimal("50.150805"));
		coordinate.setLongitude(new BigDecimal("18.836178"));
		coordinate.setTime(new GregorianCalendar(2016, 7, 01, 12, 3));
		coordinate.setProvider(Provider.NETWORK);

		em.persist(coordinate);

		coordinate = new Coordinate();
		coordinate.setDevice(device);
		coordinate.setLatitude(new BigDecimal("50.148028"));
		coordinate.setLongitude(new BigDecimal("18.835491"));
		coordinate.setTime(new GregorianCalendar(2016, 7, 01, 12, 4));
		coordinate.setProvider(Provider.NETWORK);

		em.persist(coordinate);
	}
}
