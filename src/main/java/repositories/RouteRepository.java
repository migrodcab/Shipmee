package repositories;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
	
	@Query("select r from Route r where r.origin LIKE %?2% AND r.destination LIKE %?3%")
	Collection<Route> searchRoute(String origin, String destination);
	
	@Query("select r from Route r where r.date = ?1 AND r.origin LIKE %?2% AND r.destination LIKE %?3%")
	Collection<Route> searchRouteAtDate(Date date, String origin, String destination);
	
	@Query("select r from Route r where r.departureTime >= ?1 AND r.origin LIKE %?2% AND r.destination LIKE %?3%")
	Collection<Route> searchRouteAtTime(Date time, String origin, String destination);
}
