package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false, defaultValue="-1") int routeId,
			@RequestParam(required=false, defaultValue="-1") int userId,
			@RequestParam(required=false, defaultValue="1") int page) {
		ModelAndView result;
		Page<RouteOffer> routeOffers;
		Pageable pageable;
		
		pageable = new PageRequest(page - 1, 5);
		
		routeOffers = routeOfferService.findAllByOrRouteIdAndOrUserId(routeId, userId, pageable);
		
		result = new ModelAndView("routeOffer/list");
		result.addObject("routeOffers", routeOffers.getContent());
		result.addObject("p", page);
		result.addObject("total_pages", routeOffers.getTotalPages());
		result.addObject("routeId", routeId);
		result.addObject("userId", userId);

		return result;
	}
	
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