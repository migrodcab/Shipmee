package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.RouteOffer;

@Repository
public interface RouteOfferRepository extends JpaRepository<RouteOffer, Integer> {

	@Query("select ro from RouteOffer ro where ro.route.id = ?1")
	Collection<RouteOffer> findAllByRouteId(int routeId);

}
