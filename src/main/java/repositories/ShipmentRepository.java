package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
	
	@Query("select s from Shipment s where s.origin LIKE %?1% AND s.destination LIKE %?2% AND (?3 IS NULL OR (year(s.date) = year(?3) AND month(s.date) = month(?3) AND day(s.date) = day(?3)))"
			+ "AND (?4 IS NULL OR s.departureTime >= ?4) AND (?5 IS NULL OR s.itemEnvelope = ?5  OR s.itemEnvelope LIKE 'both') "
			+ "AND (?6 IS NULL OR s.itemSize = ?6)")
	Collection<Shipment> searchShipment(String origin, String destination, Date date, Date time, String envelope, String itemSize);

}
