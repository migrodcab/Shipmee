/* AnnouncementAdministratorController.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

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

import services.RouteService;
import services.SizePriceService;
import controllers.AbstractController;
import domain.Route;
import domain.SizePrice;

@Controller
@RequestMapping("/sizePrice/user")
public class SizePriceUserController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private SizePriceService sizePriceService;
	
	@Autowired
	private RouteService routeService;
	
	// Constructors -----------------------------------------------------------
	
	public SizePriceUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int routeId) {
		ModelAndView result;
		SizePrice sizePrice;
		Route route;

		sizePrice = sizePriceService.create();
		route = routeService.findOne(routeId);
		sizePrice.setRoute(route);
		
		result = createEditModelAndView(sizePrice);

		return result;
	}

	// Edition ----------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int sizePriceId) {
		ModelAndView result;
		SizePrice sizePrice;

		sizePrice = sizePriceService.findOne(sizePriceId);		
		Assert.notNull(sizePrice);
		result = createEditModelAndView(sizePrice);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid SizePrice sizePrice, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {

			result = createEditModelAndView(sizePrice);
		} else {
			try {
				sizePriceService.save(sizePrice);				
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {

				result = createEditModelAndView(sizePrice, "sizePrice.commit.error");				
			}
		}

		return result;
	}
			
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(SizePrice sizePrice, BindingResult binding) {
		ModelAndView result;

		try {			
			sizePriceService.delete(sizePrice);
			result = new ModelAndView("redirect:list.do");						
		} catch (Throwable oops) {
			result = createEditModelAndView(sizePrice, "sizePrice.commit.error");
		}

		return result;
	}
	
	// Ancillary methods ------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(SizePrice sizePrice) {
		ModelAndView result;

		result = createEditModelAndView(sizePrice, null);
		
		return result;
	}	
	
	protected ModelAndView createEditModelAndView(SizePrice sizePrice, String message) {
		ModelAndView result;		
				
		result = new ModelAndView("sizePrice/edit");
		result.addObject("sizePrice", sizePrice);
		result.addObject("message", message);

		return result;
	}

}