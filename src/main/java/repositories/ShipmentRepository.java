package repositories;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

	@Query("select s from Shipment s where s.origin LIKE %?2% AND s.destination LIKE %?3%")
	Collection<Shipment> searchShipment(String origin, String destination);
	
	@Query("select s from Shipment s where s.date = ?1 AND s.origin LIKE %?2% AND s.destination LIKE %?3%")
	Collection<Shipment> searchShipmentAtDate(Date date, String origin, String destination);
	
	@Query("select s from Shipment s where s.departureTime >= ?1 AND s.origin LIKE %?2% AND s.destination LIKE %?3%")
	Collection<Shipment> searchShipmentAtTime(Date time, String origin, String destination);
}
