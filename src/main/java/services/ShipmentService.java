package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Shipment;
import domain.ShipmentOffer;
import domain.User;
import repositories.ShipmentRepository;

@Service
@Transactional
public class ShipmentService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ShipmentRepository shipmentRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShipmentOfferService shipmentOfferService;
	
	// Constructors -----------------------------------------------------------

	public ShipmentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Shipment create() {
		Assert.isTrue(actorService.checkAuthority("USER"),
				"Only an user can create a shipment");
		
		Shipment result;
		User user;
		Date date;
		
		result = new Shipment();
		user = userService.findByPrincipal();
		date = new Date();
		
		result.setCreator(user);
		result.setDate(date);
		
		return result;
	}
	
	public Shipment save(Shipment shipment) {
		Assert.notNull(shipment);
		Assert.isTrue(checkDates(shipment), "The departure date must be greater than the current date and the arrival date greater than the departure date.");
		Assert.isTrue(checkItemEnvelope(shipment.getItemEnvelope()), "ItemEnvelope must be open or closed.");
		
		User user;
		Date date;

		user = userService.findByPrincipal();
		date = new Date();

		if(shipment.getId() == 0) {
			shipment.setCreator(user);
			shipment.setDate(date);
			shipment.setCarried(null);
			shipment = shipmentRepository.save(shipment);
		} else {
			shipment = shipmentRepository.save(shipment);
		}
	
		return shipment;
	}
	
	public void delete(Shipment shipment) {
		Assert.notNull(shipment);
		Assert.isTrue(shipment.getId() != 0);
		Assert.isTrue(actorService.checkAuthority("USER"), "Only an user can delete shipments");
		Assert.isTrue(shipment.getCarried() == null, "An user cannot delete a shipment if it has a carrier");
		
		User user;
		Collection<ShipmentOffer> shipmentOffers;
		
		user = userService.findByPrincipal();

		Assert.isTrue(user.getId() == shipment.getCreator().getId(), "Only the user who created the shipment can delete it");
		
		shipmentOffers = shipmentOfferService.findAllByShipmentId(shipment.getId());
		for(ShipmentOffer so : shipmentOffers) {
			shipmentOfferService.delete(so.getId());
		}
						
		shipmentRepository.delete(shipment);
	}
	
	public Shipment findOne(int shipmentId) {
		Shipment result;
		
		result = shipmentRepository.findOne(shipmentId);
		
		return result;
	}
	
	public Collection<Shipment> findAll() {
		Collection<Shipment> result;

		result = shipmentRepository.findAll();

		return result;
	}
	

	// Other business methods -------------------------------------------------
	
	public void flush() {
		shipmentRepository.flush();
	}
	
	@SuppressWarnings("deprecation")
	public Collection<Shipment> searchShipment(String origin, String destination, String date, String hour, String envelope, String itemSize){
		Assert.isTrue(origin != "" && destination != "");
		Collection<Shipment> result;
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
		result = shipmentRepository.searchShipment(origin, destination, finalDate, time, envelope, itemSize);
		System.out.println(result);
		
		return result;
	}
	
	private boolean checkItemEnvelope(String itemEnvelope) {
		boolean res;
		
		res = false;

		if(itemEnvelope.equals("Open") || itemEnvelope.equals("Closed") ||
				itemEnvelope.equals("Abierto") || itemEnvelope.equals("Cerrado")) {
			res = true;
		}

		return res;
	}
	
	private boolean checkDates(Shipment shipment) {
		boolean res;
		
		res = true;
		
		if(shipment.getDate().compareTo(shipment.getDepartureTime()) >= 0) {
			res = false;
		}
		
		if(shipment.getDepartureTime().compareTo(shipment.getMaximumArriveTime()) >= 0) {
			res = false;
		}
		
		return res;
	}
	
}
