package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Shipment;
import domain.ShipmentOffer;
import domain.User;
import utilities.AbstractTest;
import utilities.UtilTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/junit.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ShipmentOfferTest extends AbstractTest {

	// Service to test --------------------------------------------------------

	@Autowired
	private ShipmentOfferService shipmentOfferService;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ShipmentService shipmentService;

	 @Autowired 
	 private UserService userService;

	// Test cases -------------------------------------------------------------

	
	/**
	 * @Test List all shipment Offers
	 * @result The shipments are list
	 */
	@Test
	public void positiveListShipmentOffer1() {
		authenticate("user2");
	
		Shipment shipment;
		Page<ShipmentOffer> shipmentOffersPage;
		Collection<ShipmentOffer> shipmentOffers;
		
		Integer shipmentId;
		Integer userId;
		Pageable pageable = new PageRequest(1 - 1, 10);
		
		shipment = shipmentService.findOne(UtilTest.getIdFromBeanName("shipment1"));
		
		shipmentId = shipment.getId();
		userId = 0;
		
		shipmentOffersPage = shipmentOfferService.findAllByOrShipmentIdAndOrUserId(shipmentId, userId, pageable);
		shipmentOffers = shipmentOfferService.findAllByShipmentId(shipmentId);
		
		Assert.isTrue(shipmentOffersPage.getNumberOfElements() == 	shipmentOffers.size());
		unauthenticate();
	}
	
	
	/**
	 * @Test List shipments Offers of a user
	 * @result The shipments of user are list
	 */
	@Test
	public void positiveListRouteOffer2() {
		authenticate("user1");
	
		Shipment shipment;
		Page<ShipmentOffer> shipmentOffers;
		Page<ShipmentOffer> shipmentOffersByUser;
		Integer shipmentId;
		Pageable pageable = new PageRequest(1 - 1, 5);

		User user1;
		Integer user1Id;

		
		shipment = shipmentService.findOne(UtilTest.getIdFromBeanName("shipment1"));
		user1 = userService.findOne(UtilTest.getIdFromBeanName("user1"));
		
		shipmentId = shipment.getId();
		user1Id = user1.getId();

		shipmentOffers = shipmentOfferService.findAllByOrShipmentIdAndOrUserId(shipmentId, user1Id, pageable);
		shipmentOffersByUser = shipmentOfferService.findAllByOrShipmentIdAndOrUserId(0, user1Id, pageable);
		
		Assert.isTrue(shipmentOffers.getNumberOfElements() == shipmentOffersByUser.getNumberOfElements());

		unauthenticate();
		
	}
	
	/**
	 * @Test List Routes Offers of a user
	 * @result The routes are not list because the user do not is logged.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negativeListRouteOffer1() {
		authenticate("user2");
	
		Shipment shipment;
		Page<ShipmentOffer> shipmentOffers;
		Page<ShipmentOffer> shipmentOffersByUser;
		Integer shipmentId;
		Pageable pageable = new PageRequest(1 - 1, 5);

		User user1;
		Integer user1Id;

		
		shipment = shipmentService.findOne(UtilTest.getIdFromBeanName("shipment1"));
		user1 = userService.findOne(UtilTest.getIdFromBeanName("user1"));
		
		shipmentId = shipment.getId();
		user1Id = user1.getId();

		shipmentOffers = shipmentOfferService.findAllByOrShipmentIdAndOrUserId(shipmentId, user1Id, pageable);
		shipmentOffersByUser = shipmentOfferService.findAllByOrShipmentIdAndOrUserId(0, user1Id, pageable);
		
		Assert.isTrue(shipmentOffers.getNumberOfElements() == shipmentOffersByUser.getNumberOfElements());


		unauthenticate();
		
	}

}