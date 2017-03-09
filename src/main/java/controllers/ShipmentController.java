package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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

		@SuppressWarnings("deprecation")
		@RequestMapping(value = "/search")
		public ModelAndView search(String origin, String destination, @RequestParam(required=false) Date date,
				@RequestParam(required=false) String hour, @RequestParam(required=false) String envelope,
				@RequestParam(required=false) String itemSize) {
			ModelAndView result;
			SimpleDateFormat formatter;
			Date time;
			Collection<Shipment> shipments;
			
			formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			shipments = new HashSet<Shipment>();
			
			if(date!=null){
				try {
					time = formatter.parse(date.getDate()+"/"+date.getMonth()+"/"+date.getYear()+" "+hour);
					shipments = shipmentService.searchShipment(origin, destination, date, time, envelope, itemSize);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
						
			result = new ModelAndView("search/list");
			result.addObject("shipments", shipments);

			return result;
			}
		
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView seeThread() {
			ModelAndView result;


			result = new ModelAndView("shipment/list");
			
			return result;

		}
}