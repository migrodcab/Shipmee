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
		result.setDate(date);
		
		return result;
	}
	
	public Route save(Route route) {
		Assert.notNull(route);
		Assert.isTrue(checkItemEnvelope(route.getItemEnvelope()), "ItemEnvelope must be open, closed or both.");
		if(route.getVehicle() != null) {
			Assert.isTrue(route.getCreator().getId() == route.getVehicle().getUser().getId(), "Both Ids must be the same.");
		}
		
		User user;
		Date date;
		
		user = userService.findByPrincipal();
		date = new Date();
		
		if(route.getId() == 0) {
			route.setCreator(user);
			route.setDate(date);
			
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
	
	public void flush() {
		routeRepository.flush();
	}
	
	private boolean checkItemEnvelope(String itemEnvelope) {
		boolean res;
		
		res = false;

		if(itemEnvelope.equals("Open") || itemEnvelope.equals("Closed") ||
				itemEnvelope.equals("Both") || itemEnvelope.equals("Abierto") ||
				itemEnvelope.equals("Cerrado") || itemEnvelope.equals("Ambos")) {
			res = true;
		}

		return res;
	}
	
}
