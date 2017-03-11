package repositories;

import java.awt.print.Pageable;
import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.RouteOffer;

@Repository
public interface RouteOfferRepository extends JpaRepository<RouteOffer, Integer> {

	@Query("select ro from RouteOffer ro where ro.route.id = ?1")
	Collection<RouteOffer> findAllByRouteId(int routeId);
	
	@Query("select ro from RouteOffer ro where (?1 IS NULL OR ro.route.id = ?1)"
			+ " AND (?2 IS NULL OR ro.user.id = ?2)"
			+ " ORDER BY ro.rejectedByCarrier ASC")
	Page<RouteOffer> findAllByRouteIdAndUserId(int routeId, int userId, Pageable page);

}
