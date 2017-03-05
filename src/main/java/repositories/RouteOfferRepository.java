package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.RouteOffer;

@Repository
public interface RouteOfferRepository extends JpaRepository<RouteOffer, Integer> {

}
