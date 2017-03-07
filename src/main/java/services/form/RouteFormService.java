package services.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Route;
import domain.form.RouteForm;
import services.RouteService;

@Service
@Transactional
public class RouteFormService {

	// Supporting services ----------------------------------------------------

	@Autowired
	private RouteService routeService;
	
	// Constructors -----------------------------------------------------------

	public RouteFormService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public RouteForm create() {
		RouteForm result;
		
		result = new RouteForm();
		
		result.setRouteId(0);
		
		return result;
	}
	
	public Route reconstruct(RouteForm routeForm) {
		Route result;
		Date departureTime, arriveTime;
		
		departureTime = null;
		arriveTime = null;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		
		try {
			departureTime = formatter.parse(routeForm.getDepartureTime());
			arriveTime = formatter.parse(routeForm.getArriveTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
				
		if (routeForm.getRouteId() == 0) {
			result = routeService.create();
			
			result.setOrigin(routeForm.getOrigin());
			result.setDestination(routeForm.getDestination());
			result.setItemEnvelope(routeForm.getItemEnvelope());
			result.setVehicle(routeForm.getVehicle());
			result.setArriveTime(arriveTime);
			result.setDepartureTime(departureTime);
			
			
		} else if(routeForm.getRouteId() != 0) {			
			result = routeService.findOne(routeForm.getRouteId());
			result.setOrigin(routeForm.getOrigin());
			result.setDestination(routeForm.getDestination());
			result.setItemEnvelope(routeForm.getItemEnvelope());
			result.setVehicle(routeForm.getVehicle());
			result.setArriveTime(arriveTime);
			result.setDepartureTime(departureTime);
		} else {
			result = null;
		}
		
		return result;
	}

	public RouteForm findOne(int routeId) {
		RouteForm result;
		Route route;
		String arriveTime, departureTime;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		
		result = this.create();
		route = routeService.findOne(routeId);
		
		arriveTime = formatter.format(route.getArriveTime());
		departureTime = formatter.format(route.getDepartureTime());
		
		result.setOrigin(route.getOrigin());
		result.setDestination(route.getDestination());
		result.setItemEnvelope(route.getItemEnvelope());
		result.setVehicle(route.getVehicle());
		result.setArriveTime(arriveTime);
		result.setDepartureTime(departureTime);
		
		return result;
	}

	public void delete(RouteForm routeForm) {
		Route route;
		route = routeService.findOne(routeForm.getRouteId());
		routeService.delete(route);
	}
}
