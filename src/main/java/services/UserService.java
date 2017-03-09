package services;


import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Route;
import domain.User;
import repositories.UserRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class UserService {
	//Managed repository -----------------------------------------------------
	
	@Autowired
	private UserRepository userRepository;

	//Supporting services ----------------------------------------------------
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private RouteService routeService;
	
	//Constructors -----------------------------------------------------------

	public UserService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	/**
	 * 
	 * @param user - Current user
	 * @return - Updated user
	 * 
	 * THIS VERSION IS DONE FOR PRIOR ANY USER CRUD DEVELOPMENT (EXPECTED FOR SPRINT 2).
	 * MUST BE REDONE.
	 * CHECK THAT selectRoute STILL WORKS!!
	 */
	public User save(User user){
		
		Assert.notNull(user);
		
		user = userRepository.save(user);
		
		return user;
	}
	
	//Other business methods -------------------------------------------------

	/**
	 * Devuelve el user que está realizando la operación
	 */
	public User findByPrincipal(){
		User result;
		UserAccount userAccount;
		
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = userRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);
		
		return result;
	}

	public Collection<User> findAllByRoutePurchased(int routeId) {
		Collection<User> result;
		
		result = userRepository.findAllByRoutePurchased(routeId);

		return result;
	}
	
	public void selectRoute(int routeId){

		Assert.isTrue(routeId != 0);
		Assert.isTrue(actorService.checkAuthority("USER"), "Only a user can select a route");
		/*
		 * Here comes session restrictions and other stuff.
		 * I don't know if there is something missing above
		 */
		
		
		Route route = routeService.findOne(routeId);
		User client = findByPrincipal();
		Date now = new Date();
		
		Assert.notNull(route); // route is not null.
		Assert.isTrue(route.getDate().before(now)); // route was created in the past.
		Assert.isTrue(route.getDepartureTime().after(now)); // route's departure time is future.
		Assert.isTrue(route.getArriveTime().after(now)); // route's arrival time is future.
		Assert.notNull(client);
		/*
		 * Here comes the assert to:
		 * 	check that a user's package can be carried.
		 */
		
		client.getRoutes().add(route);
		save(client);
		
		/*
		 * Here comes the notification to the creator (Still not developed).
		 */
	}

}
