package services;

import java.util.Collection;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Route;
import domain.User;
import repositories.RouteRepository;

@Service
@Transactional
public class RouteService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private RouteRepository routeRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private UserService userService;
	
	
	// Constructors -----------------------------------------------------------

	public RouteService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Route create() {
		Assert.isTrue(actorService.checkAuthority("USER"),
				"Only an user can create a route");
		
		Route result;
		User user;
		Date date;
		
		result = new Route();
		user = userService.findByPrincipal();
		date = new Date();
		
		result.setCreator(user);
		result.setVehicle(null);
		result.setDate(date);
		
		return result;
	}
	
	public Route save(Route route) {
		Assert.notNull(route);
		
		User user;
		Date date;
		
		user = userService.findByPrincipal();
		date = new Date();
		
		route.setCreator(user);
		
		if(route.getId() == 0) {
			route.setDate(date);
			route.setItemEnvelope("Close");
			route.setVehicle(null);
			
			route = routeRepository.save(route);
			
		} else {
			route = routeRepository.save(route);
		}
		
		
			
		return route;
	}
	
	/*public void delete(Club club, int managerId) {
		Assert.notNull(club);
		Assert.isTrue(club.getId() != 0);
		Assert.isTrue(actorService.checkAuthority("MANAGER"), "Only a manager can delete clubes");
		Manager preSave, postSave;
		
		if(actorService.checkAuthority("MANAGER")) {
			Manager manager;
			
			manager = userService.findByPrincipal();
			Assert.isTrue(club.getManager().getId() == manager.getId(), "Only the manager of this club can save it");
		}
		
		preSave = userService.findByPrincipal();
		postSave = userService.findOne(managerId);
		
		Assert.isTrue(postSave.getClub() == null, "El nuevo Manager no debe ser dueño de un club");
		
		preSave.setClub(null);
		userService.saveFromOthers(preSave);
		
		club.setManager(postSave);
		club = routeRepository.save(club);
		
		postSave.setClub(club);
		userService.saveFromOthers(postSave);
		
	}*/
	
	public Route findOne(int routeId) {
		Route result;
		
		result = routeRepository.findOne(routeId);
		
		return result;
	}
	
	public Collection<Route> findAll() {
		Collection<Route> result;

		result = routeRepository.findAll();

		return result;
	}
	

	// Other business methods -------------------------------------------------
	
	public Collection<Route> searchRoute(String origin, String destination, Date date, Date time, String envelope){
		Assert.isTrue(origin != "" && destination != "");
		Collection<Route> result;
		
		result = routeRepository.searchRoute(origin, destination, date, time, envelope);
		
		return result;
	}
	
	public void flush() {
		routeRepository.flush();
	}
	
}
