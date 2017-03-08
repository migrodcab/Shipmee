package repositories;

import java.util.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
	
	@Query("select r from Route r where r.origin LIKE %?1% AND r.destination LIKE %?2% AND (?3 IS NULL OR r.date = ?3)"
			+ "AND (?4 IS NULL OR r.departureTime = ?4) AND (?5 IS NULL OR r.itemEnvelope = ?5)")
	Collection<Route> searchRoute(String origin, String destination, Date date, Date time, String envelope);
	
}
