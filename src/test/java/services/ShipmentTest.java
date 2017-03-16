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
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ShipmentTest extends AbstractTest{
	
	// Service to test --------------------------------------------------------
	
	@Autowired
	private ShipmentService shipmentService;
	
	
	// Supporting services ----------------------------------------------------	
	
	/*@Autowired
	private UserService userService;*/
	
	// Test cases -------------------------------------------------------------	
	
	/**
	 * @Test Carry a Shipment
	 * @result The shipment is associated to the carrier
	 */
	@Test
	public void possitiveCarryShipmentTest() {
		authenticate("user1");
		Shipment shipment;
		ShipmentOffer shipmentOffer;
		
		shipment = shipmentService.findOne(UtilTest.getIdFromBeanName("shipment1"));
		Assert.isTrue(shipment.getCarried()==null);
		
		shipmentOffer = shipmentService.carryShipment(UtilTest.getIdFromBeanName("shipment1"));
		Assert.isTrue(shipmentOffer.getId()!=0);
		Assert.isTrue(shipmentOffer.getShipment().equals(shipment));
		
		unauthenticate();
	}

	/**
	 * @Test Other business methods
	 * @result The methods returns the expected values
	 */
	@Test
	public void possitiveCommentOtherMethodsTest() {
		authenticate("user1");

		unauthenticate();
	}

	/**
	 * @Test Edition of comments
	 * @result We try to edit an comment so
	 *         <code>IllegalArgumentException</code> is expected
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negativeCRUDTes() {		
		throw new IllegalArgumentException();
	}

	/**
	 * @Test Comments created by User
	 * @result We try to get the comments of null user so
	 *         <code>IllegalArgumentException</code> is expected
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negativeCommentOtherMethodsTest() {
		throw new IllegalArgumentException();
	}

	/**
	 * @Test Comments created by Given User
	 * @result We try to get the comments of null user so
	 *         <code>IllegalArgumentException</code> is expected
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negativeCommentOtherMethodsTest1() {
		throw new IllegalArgumentException();
	}

}