package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Route;
import domain.RouteOffer;
import repositories.RouteOfferRepository;

@Service
@Transactional
public class RouteOfferService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private RouteOfferRepository routeOfferRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;
		
	// Constructors -----------------------------------------------------------

	public RouteOfferService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	
	public void delete(RouteOffer routeOffer) {
		Assert.notNull(routeOffer);
		Assert.isTrue(routeOffer.getId() != 0);
		Assert.isTrue(actorService.checkAuthority("USER"), "Only an user can delete route offers");

		routeOfferRepository.delete(routeOffer);
	}
	
	public RouteOffer findOne(int routeOfferId) {
		RouteOffer result;
		
		result = routeOfferRepository.findOne(routeOfferId);
		
		return result;
	}

	// Other business methods -------------------------------------------------
	
	public void flush() {
		routeOfferRepository.flush();
	}

	public Collection<RouteOffer> findAllByRouteId(int routeId) {
		Collection<RouteOffer> result;
		
		result = routeOfferRepository.findAllByRouteId(routeId);

		return result;
	}
	
	public void accept(int routeOfferId){
		
		Assert.isTrue(routeOfferId != 0);
		Assert.isTrue(actorService.checkAuthority("USER"), "Only a user can select a shipment");
		
		RouteOffer routeOffer = findOne(routeOfferId);
		Route route = routeOffer.getRoute();
		
		Assert.notNull(route);
		Assert.isTrue(route.getCreator().equals(actorService.findByPrincipal()));
		
		/*
		 * Here comes:
		 * - Update price of the route.
		 * - Update user with his role.
		 * - Save changes.
		 * - Notification to the client.
		 */
		
		
	}
	
}
