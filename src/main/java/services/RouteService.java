package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Route;
import domain.RouteOffer;
import domain.SizePrice;
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
	
	@Autowired
	private SizePriceService sizePriceService;
	
	@Autowired
	private RouteOfferService routeOfferService;
	
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
		Assert.isTrue(checkDates(route), "The departure date must be greater than the current date and the arrival date greater than the departure date.");
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
	
	public void delete(Route route) {
		Assert.notNull(route);
		Assert.isTrue(route.getId() != 0);
		Assert.isTrue(actorService.checkAuthority("USER"), "Only an user can delete routes");

		User user;
		Collection<User> users;
		Collection<SizePrice> sizePrices;
		Collection<RouteOffer> routeOffers;
		
		user = userService.findByPrincipal();
		users = userService.findAllByRoutePurchased(route.getId());

		Assert.isTrue(user.getId() == route.getCreator().getId(), "Only the user who created the route can delete it");
		Assert.isTrue(users.isEmpty(), "User can not delete a route if he has purchasers");

		sizePrices = sizePriceService.findAllByRouteId(route.getId());
		for(SizePrice s : sizePrices) {
			sizePriceService.delete(s);
		}
		
		routeOffers = routeOfferService.findAllByRouteId(route.getId());
		for(RouteOffer ro : routeOffers) {
			routeOfferService.delete(ro.getId());
		}
						
		routeRepository.delete(route);
	}
	
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
	
	@SuppressWarnings("deprecation")
	public Collection<Route> searchRoute(String origin, String destination, String date, String hour, String envelope, String itemSize){
		Assert.isTrue(origin != "" && destination != "");
		Collection<Route> result;
		SimpleDateFormat formatter;
		Date time;
		Date finalDate;
		
		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		time = null;
		finalDate = null;
		
		if(date!=null){
			try {
				finalDate = formatter.parse(date+" 00:00");
				time = formatter.parse(finalDate.getDate()+"/"+finalDate.getMonth()+"/"+finalDate.getYear()+" "+hour);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(origin+" - "+destination);
		result = routeRepository.searchRoute(origin, destination, finalDate, time, envelope, itemSize);
		System.out.println(result);
		return result;
	}
	
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
	
	private boolean checkDates(Route route) {
		boolean res;
		
		res = true;
		
		if(route.getDate().compareTo(route.getDepartureTime()) >= 0) {
			res = false;
		}
		
		if(route.getDepartureTime().compareTo(route.getArriveTime()) >= 0) {
			res = false;
		}
		
		return res;
	}
	
}
