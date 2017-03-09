package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ShipmentOffer;

@Repository
public interface ShipmentOfferRepository extends JpaRepository<ShipmentOffer, Integer> {

	@Query("select so from ShipmentOffer so where so.shipment.id = ?1")
	Collection<ShipmentOffer> findAllByShipmentId(int shipmentId);
}
