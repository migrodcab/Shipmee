package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Route;
import domain.RouteOffer;
import domain.User;
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
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RouteService routeService;
	
	// Constructors -----------------------------------------------------------

	public RouteOfferService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	
	public RouteOffer create(int routeId) {
		RouteOffer res;
		Route route;
		
		route = routeService.findOne(routeId);
		Assert.notNull(route, "service.routeOffer.create.isNullRoute");
		Assert.isTrue(actorService.checkAuthority("USER"), "service.routeOffer.create.notIsUserAuthority");
	
		
		res = new RouteOffer();
		res.setRoute(route);
		res.setUser(userService.findByPrincipal());
		
		return res;
	}
	
	public RouteOffer createFromClone(int routeOfferId){
		RouteOffer res, act;
		act = this.findOne(routeOfferId);
		Assert.notNull(act, "service.routeOffer.createFromClone.isNullROute");
		
		res = this.create(act.getRoute().getId());
		res.setAmount(act.getAmount());
		res.setDescription(act.getDescription());
		
		return res;
	}
	
	
	public RouteOffer save(RouteOffer input) {
		User actUser;	
		RouteOffer tmp;

		
		Assert.notNull(input, "service.routeOffer.save.isNull");
		Assert.isTrue(actorService.checkAuthority("USER"), "service.routeOffer.save.notIsUserAuthority");
		Assert.isTrue(! input.getUser().equals(input.getRoute().getCreator()), "service.routeOffer.save.equalsCreatorAndProposer");
		
		actUser = userService.findByPrincipal();
		
		if (actUser.equals(input.getUser())){  // User that create route
			if(input.getId() != 0){
				tmp = this.findOne(input.getId());
				Assert.notNull(tmp, "service.routeOffer.save.creator.dontFindID");
				Assert.isTrue(tmp.getUser().equals(actUser), "service.routeOffer.save.creator.fake");
				Assert.isTrue(!tmp.getAcceptedByCarrier() && !tmp.getRejectedByCarrier(), "service.routeOffer.save.creator.isRejectedOrAccepted");
			}else{
				tmp = this.create(input.getRoute().getId());
			}
			
			tmp.setAmount(input.getAmount());
			tmp.setDescription(input.getDescription());
		}else if(actUser.equals(input.getRoute().getCreator())){	// User that put the offer
			Assert.isTrue(input.getId() != 0, "service.routeOffer.save.ProposerCreating"); // The routeCreator can't
			
			tmp = this.findOne(input.getId());
			Assert.notNull(tmp, "service.routeOffer.save.trans.dontFindID");
			Assert.isTrue(tmp.getRoute().getCreator().equals(actUser), "service.routeOffer.save.trans.fake");
			tmp.setAcceptedByCarrier(input.getAcceptedByCarrier());
			tmp.setRejectedByCarrier(input.getRejectedByCarrier());
		}else{
			Assert.isTrue(false, "service.routeOffer.save.userNotPermitted");
			return null;
		}
		tmp = routeOfferRepository.save(tmp);
		
		return tmp;		
	}
		
		
	
	
	public void delete(RouteOffer routeOffer) {
		Assert.notNull(routeOffer);
		Assert.isTrue(routeOffer.getId() != 0);
		Assert.isTrue(actorService.checkAuthority("USER"), "Only an user can delete route offers");

		routeOfferRepository.delete(routeOffer);
	}
	
	public RouteOffer findOne(int routeOfferId) {
		RouteOffer result;
		User actUser;
		Assert.isTrue(actorService.checkAuthority("USER"), "service.routeOffer.findOne.notIsUserAuthority");

		result = routeOfferRepository.findOne(routeOfferId);

		if (result != null) {
			actUser = userService.findByPrincipal();

			if (!(actUser.equals(result.getUser()) || actUser.equals(result.getRoute().getCreator()))) {
				result = null;
			}
		}

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
	
}
