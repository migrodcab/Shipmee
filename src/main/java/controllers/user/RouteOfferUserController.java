package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Route;
import domain.RouteOffer;
import services.RouteOfferService;

@Controller
@RequestMapping("/routeOffer/user")
public class RouteOfferUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private RouteOfferService routeOfferService;

	// Constructors -----------------------------------------------------------

	public RouteOfferUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int routeId) {
		ModelAndView result;
		RouteOffer routeOffer;

		routeOffer = routeOfferService.create(routeId);
		result = createEditModelAndView(routeOffer);

		return result;
	}

	@RequestMapping(value = "/createClone", method = RequestMethod.GET)
	public ModelAndView createFromClone(@RequestParam int routeOfferId) {
		ModelAndView result;
		RouteOffer routeOffer;

		routeOffer = routeOfferService.createFromClone(routeOfferId);
		result = createEditModelAndView(routeOffer);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int routeOfferId) {
		ModelAndView result;
		RouteOffer routeOffer;

		routeOffer = routeOfferService.findOne(routeOfferId);
		Assert.notNull(routeOffer, "controller.user.routeOffer.edit.isNull");
		result = createEditModelAndView(routeOffer);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid RouteOffer routeOffer, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(routeOffer);
		} else {
			try {
				routeOffer = routeOfferService.save(routeOffer);

				result = new ModelAndView("redirect:details.do?routeId=" + routeOffer.getRoute().getId());
			} catch (Throwable oops) {
				result = createEditModelAndView(routeOffer, "routeOffer.commit.error");
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	public ModelAndView accept(@RequestParam int routeOfferId){
		ModelAndView result;
		
		RouteOffer routeOffer = routeOfferService.findOne(routeOfferId);
		Route route = routeOffer.getRoute();
		
		try{
			routeOfferService.accept(routeOfferId);
			// This reditect may be change to other url.
			result = new ModelAndView("redirect:../search.do?origin=" + route.getOrigin() + "&destination=" + route.getDestination());
		}catch(Throwable oops){
			result = createEditModelAndView(routeOffer, "routeOffer.commit.error");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/deny", method = RequestMethod.GET)
	public ModelAndView deny(@RequestParam int routeOfferId){
		ModelAndView result;
		
		RouteOffer routeOffer = routeOfferService.findOne(routeOfferId);
		Route route = routeOffer.getRoute();
		
		try{
			routeOfferService.deny(routeOfferId);
			// This reditect may be change to other url.
			result = new ModelAndView("redirect:../search.do?origin=" + route.getOrigin() + "&destination=" + route.getDestination());
		}catch(Throwable oops){
			result = createEditModelAndView(routeOffer, "routeOffer.commit.error");
		}
		
		return result;
	}
	
	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(RouteOffer input) {
		ModelAndView result;

		result = createEditModelAndView(input, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(RouteOffer input, String message) {
		ModelAndView result;

		result = new ModelAndView("routeOffer/edit");
		result.addObject("routeOffer", input);
		result.addObject("message", message);

		return result;
	}

}