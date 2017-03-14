package controllers;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Route;
import services.RouteService;
import services.UserService;

@Controller
@RequestMapping("/route")
public class RouteController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private RouteService routeService;
	
	@Autowired
	private UserService userService;	
	
	// Constructors -----------------------------------------------------------
	
	public RouteController() {
		super();
	}
		
	// Search ------------------------------------------------------------------		

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(String origin, String destination, @RequestParam(required=false) String date,
			@RequestParam(required=false) String hour, @RequestParam(required=false) String envelope,
			@RequestParam(required=false) String itemSize) {
		ModelAndView result;
		Collection<Route> routes;
		
		routes = new HashSet<Route>();
		
		routes = routeService.searchRoute(origin, destination, date, hour, envelope, itemSize);
				
		result = new ModelAndView("route/search");
		result.addObject("routes", routes);
		result.addObject("origin", origin);
		result.addObject("destination", destination);

		return result;
	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView seeThread(@RequestParam int id) {
		ModelAndView result;
		
		result = createListModelAndView(id);
		
		return result;

	}
	
	private ModelAndView createListModelAndView(int id){
		ModelAndView result;
		Route route;
		
		route = routeService.findOne(id);
		
		Integer loggedUserId;

		
		loggedUserId = userService.findByPrincipal().getId();

		result = new ModelAndView("route/display");
		result.addObject("route", route);
		result.addObject("loggedUserId", loggedUserId);

		return result;
	}
}