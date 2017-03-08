package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Shipment;
import repositories.ShipmentRepository;

@Service
@Transactional
public class ShipmentService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ShipmentRepository shipmentRepository;

	// Supporting services ----------------------------------------------------

	
	// Constructors -----------------------------------------------------------

	public ShipmentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Shipment create() {
		Shipment result;
		
		result = new Shipment();
		
		return result;
	}
	
	public Shipment save(Shipment shipment) {
		Assert.notNull(shipment);
			
		shipment = shipmentRepository.save(shipment);

		return shipment;
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
	
	public Collection<Shipment> searchShipment(String origin, String destination, Date date, Date time, String envelope, String itemSize){
		Assert.isTrue(origin != "" && destination != "");
		Collection<Shipment> result;
		
		result = shipmentRepository.searchShipment(origin, destination, date, time, envelope, itemSize);
		
		return result;
	}
	
	public void flush() {
		shipmentRepository.flush();
	}
	
}
