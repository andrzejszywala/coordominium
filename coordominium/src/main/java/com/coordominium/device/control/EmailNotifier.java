package com.coordominium.device.control;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class EmailNotifier {

	@PersistenceContext
	EntityManager em;
	
	@Schedule(minute="*/1",hour="*", persistent=false)
	public void timeout() {
//		em.createNativeQuery("        SELECT "
//							+ "	            d.id"
//							+ "	        FROM"
//							+ "	            coordinate c"
//							+ "	                JOIN"
//							+ "	            crd_device d ON c.DEVICE_ID = d.id"
//							+ "	                JOIN"
//							+ "	            crd_device_notif n ON n.DEVICE_ID = d.id"
//							+ "	        WHERE"
//							+ "	            time BETWEEN CONCAT(CURRENT_DATE, ' ', start) AND CASE"
//							+ "	                WHEN start > end THEN CONCAT(CURRENT_DATE + INTERVAL 1 DAY, ' ', end)"
//							+ "	                ELSE CONCAT(CURRENT_DATE, ' ', end)"
//							+ "	            END"
//							+ "	        GROUP BY d.id"
//							+ "	        HAVING COUNT(*) > 1)")
//		.getResultList();
	}
	
}
