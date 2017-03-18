package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Shipment;
import domain.ShipmentOffer;
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


	// Test cases -------------------------------------------------------------

	/**
	 * @Test Accept a Shipment Offer
	 * @result The user accepts a shipment offer
	 */
	@Test
	public void shipmentOfferAcceptPositive1() {
		authenticate("user2");
		
		Shipment shipment;
		ShipmentOffer ro;
		
		shipment = shipmentService.findOne(UtilTest.getIdFromBeanName("shipment1"));
		ro = shipmentOfferService.findOne(UtilTest.getIdFromBeanName("shipmentOffer4"));
		
		shipmentOfferService.accept(ro.getId());
		
		ro = shipmentOfferService.findOne(ro.getId());
		
		Assert.isTrue(ro.getAcceptedBySender() && !ro.getRejectedBySender());
		
		for(ShipmentOffer a:shipmentOfferService.findAllByShipmentId(shipment.getId())){
			if(a.getId() != ro.getId())
				Assert.isTrue(!a.getAcceptedBySender());
		}
	}
	
	/**
	 * @Test Accept a Shipment Offer
	 * @result The user tries to accept their own shipment offer
	 */
	@Test(expected = IllegalArgumentException.class)
	public void shipmentOfferAcceptNegative1() {
		authenticate("user2");
		
		ShipmentOffer ro;
		
		ro = shipmentOfferService.findOne(UtilTest.getIdFromBeanName("shipmentOffer3"));
		
		authenticate("user1");
		
		shipmentOfferService.accept(ro.getId());
		
		ro = shipmentOfferService.findOne(ro.getId());
		
		Assert.isTrue(ro.getAcceptedBySender() && !ro.getRejectedBySender());
		
	}
	
	/**
	 * @Test Accept a Shipment Offer
	 * @result The user tries to accept an offer without being logged in
	 */
	@Test(expected = IllegalArgumentException.class)
	public void shipmentOfferAcceptNegative2() {
		authenticate("user2");
		
		ShipmentOffer ro;
		
		ro = shipmentOfferService.findOne(UtilTest.getIdFromBeanName("shipmentOffer3"));
		
		unauthenticate();
		
		shipmentOfferService.accept(ro.getId());
		
		authenticate("user2");

		ro = shipmentOfferService.findOne(ro.getId());
		
		Assert.isTrue(ro.getAcceptedBySender() && !ro.getRejectedBySender());
	}
	
	/**
	 * @Test Accept a Shipment Offer
	 * @result The user tries to accept an offer that is rejected
	 */
	@Test(expected = IllegalArgumentException.class)
	public void shipmentOfferAcceptNegative3() {
		authenticate("user2");
		
		ShipmentOffer ro;
		
		ro = shipmentOfferService.findOne(UtilTest.getIdFromBeanName("shipmentOffer2"));			
		shipmentOfferService.accept(ro.getId());
		ro = shipmentOfferService.findOne(ro.getId());
		
		Assert.isTrue(ro.getAcceptedBySender() && !ro.getRejectedBySender());
	}
	
	/**
	 * @Test Accept a Shipment Offer
	 * @result The user tries to accept an offer that contain a shipmentOffer accepted
	 */
	@Test(expected = IllegalArgumentException.class)
	public void shipmentOfferAcceptNegative4() {
		authenticate("user2");
		
		ShipmentOffer ro;
		
		ro = shipmentOfferService.findOne(UtilTest.getIdFromBeanName("shipmentOffer6"));				
		shipmentOfferService.accept(ro.getId());
		ro = shipmentOfferService.findOne(ro.getId());
		
		Assert.isTrue(ro.getAcceptedBySender() && !ro.getRejectedBySender());
	}
	
	
	/**
	 * @Test Deny a Shipment Offer
	 * @result The user denies a shipment offer
	 */
	@Test
	public void shipmentOfferDenyPositive1() {
		authenticate("user2");
		
		ShipmentOffer ro;

		ro = shipmentOfferService.findOne(UtilTest.getIdFromBeanName("shipmentOffer3"));
		
		shipmentOfferService.deny(ro.getId());
		
		ro = shipmentOfferService.findOne(ro.getId());
		
		Assert.isTrue(!ro.getAcceptedBySender() && ro.getRejectedBySender());
	}
	
	
	/**
	 * @Test Deny a Shipment Offer
	 * @result The user tries to deny their own shipment offer
	 */
	@Test(expected = IllegalArgumentException.class)
	public void shipmentOfferDenyNegative1() {
		authenticate("user1");
		
		ShipmentOffer ro;

		ro = shipmentOfferService.findOne(UtilTest.getIdFromBeanName("shipmentOffer3"));
				
		shipmentOfferService.deny(ro.getId());
		
		ro = shipmentOfferService.findOne(ro.getId());
		
		Assert.isTrue(!ro.getAcceptedBySender() && ro.getRejectedBySender());
	}
	
	/**
	 * @Test Deny a Shipment Offer
	 * @result The user tries to deny an offer without being logged in
	 */
	@Test(expected = IllegalArgumentException.class)
	public void shipmentOfferDenyNegative2() {
		authenticate("user2");
		
		ShipmentOffer ro;

		ro = shipmentOfferService.findOne(UtilTest.getIdFromBeanName("shipmentOffer3"));
		
		unauthenticate();
		
		shipmentOfferService.deny(ro.getId());
		
		authenticate("user2");
		
		ro = shipmentOfferService.findOne(ro.getId());
		
		Assert.isTrue(!ro.getAcceptedBySender() && ro.getRejectedBySender());
	}
	
	/**
	 * @Test Deny a Shipment Offer
	 * @result The user tries to deny an offer that is rejected
	 */
	@Test(expected = IllegalArgumentException.class)
	public void shipmentOfferDenyNegative3() {
		authenticate("user2");
		
		ShipmentOffer ro;
		
		ro = shipmentOfferService.findOne(UtilTest.getIdFromBeanName("shipmentOffer2"));				
		shipmentOfferService.deny(ro.getId());
		ro = shipmentOfferService.findOne(ro.getId());
		
		Assert.isTrue(!ro.getAcceptedBySender() && ro.getRejectedBySender());
	}
	
	/**
	 * @Test Deny a Shipment Offer
	 * @result The user tries to deny an offer that is accepted
	 */
	@Test(expected = IllegalArgumentException.class)
	public void shipmentOfferDenyNegative4() {
		authenticate("user3");
		
		ShipmentOffer ro;
		
		ro = shipmentOfferService.findOne(UtilTest.getIdFromBeanName("shipmentOffer5"));				
		shipmentOfferService.deny(ro.getId());
		ro = shipmentOfferService.findOne(ro.getId());
		
		Assert.isTrue(!ro.getAcceptedBySender() && ro.getRejectedBySender());
	}
	

}
