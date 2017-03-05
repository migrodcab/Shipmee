package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ShipmentOffer;

@Repository
public interface ShipmentOfferRepository extends JpaRepository<ShipmentOffer, Integer> {

}
