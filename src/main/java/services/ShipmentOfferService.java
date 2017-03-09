package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.ShipmentOffer;
import repositories.ShipmentOfferRepository;

@Service
@Transactional
public class ShipmentOfferService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ShipmentOfferRepository shipmentOfferRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------

	public ShipmentOfferService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	
	public void delete(ShipmentOffer shipmentOffer) {
		Assert.notNull(shipmentOffer);
		Assert.isTrue(shipmentOffer.getId() != 0);
		Assert.isTrue(actorService.checkAuthority("USER"), "Only an user can delete shipment offers");

		shipmentOfferRepository.delete(shipmentOffer);
	}
	
	public ShipmentOffer findOne(int shipmentOfferId) {
		ShipmentOffer result;
		
		result = shipmentOfferRepository.findOne(shipmentOfferId);
		
		return result;
	}

	// Other business methods -------------------------------------------------
	
	public void flush() {
		shipmentOfferRepository.flush();
	}

	public Collection<ShipmentOffer> findAllByShipmentId(int shipmentId) {
		Collection<ShipmentOffer> result;
		
		result = shipmentOfferRepository.findAllByShipmentId(shipmentId);

		return result;
	}
	
}
