package controllers;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Shipment;
import services.ShipmentService;

@Controller
@RequestMapping("/shipment")
public class ShipmentController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private ShipmentService shipmentService;	


	// Constructors -----------------------------------------------------------
	
	public ShipmentController() {
		super();
	}
		
	// Search ------------------------------------------------------------------		

		@RequestMapping(value = "/search")
		public ModelAndView search(String origin, String destination, @RequestParam(required=false) String date,
				@RequestParam(required=false) String hour, @RequestParam(required=false) String envelope,
				@RequestParam(required=false) String itemSize) {
			ModelAndView result;
			Collection<Shipment> shipments;

			shipments = new HashSet<Shipment>();
			
			shipments = shipmentService.searchShipment(origin, destination, date, hour, envelope, itemSize);
						
			result = new ModelAndView("shipment/search");
			result.addObject("shipments", shipments);
			result.addObject("origin", origin);
			result.addObject("destination", destination);

			return result;
			}
		
		@RequestMapping(value = "/display", method = RequestMethod.GET)
		public ModelAndView seeThread(@RequestParam int shipmentId) {
			ModelAndView result;
			
			result = createListModelAndView(shipmentId);
			
			return result;

		}
		
		private ModelAndView createListModelAndView(int shipmentId){
			ModelAndView result;
			Shipment shipment;
			
			shipment = shipmentService.findOne(shipmentId);
			
			
			result = new ModelAndView("shipment/display");
			result.addObject("shipment", shipment);

			return result;
		}
}