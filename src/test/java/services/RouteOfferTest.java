package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Route;
import domain.RouteOffer;
import domain.User;
import utilities.AbstractTest;
import utilities.UtilTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/junit.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class RouteOfferTest extends AbstractTest {

	// Service to test --------------------------------------------------------

	@Autowired
	private RouteOfferService routeOfferService;

	// Supporting services ----------------------------------------------------

	@Autowired
	private RouteService routeService;

	 @Autowired 
	 private UserService userService;

	// Test cases -------------------------------------------------------------

	/**
	 * @Test Create a Route Offer
	 * @result The route has a new route offer
	 */
	@Test
	public void positiveCreateRouteOffer1() {
		authenticate("user1");
		
		RouteOffer result;
		Route route;
		Collection<RouteOffer> routeOffers;
		int sizePreCreate, sizePostCreate;
		
		route = routeService.findOne(UtilTest.getIdFromBeanName("route1"));
		routeOffers = routeOfferService.findAllByRouteId(route.getId());
		sizePreCreate = routeOffers.size();
		
		unauthenticate();
		authenticate("user2");
		
		result = routeOfferService.create(route.getId());
		result.setAmount(1);
		result.setDescription("Test");
		result = routeOfferService.save(result);
		
		unauthenticate();
		authenticate("user1");
		
		route = routeService.findOne(UtilTest.getIdFromBeanName("route1"));
		routeOffers = routeOfferService.findAllByRouteId(route.getId());
		sizePostCreate = routeOffers.size();
		
		Assert.isTrue(sizePostCreate == sizePreCreate + 1);
		Assert.isTrue(routeOffers.contains(result));
		
		unauthenticate();
	}
	
	/**
	 * @Test Create a Route Offer
	 * @result The user tries to create a route offer with acceptedByCarrier to true
	 */
	@Test
	public void positiveCreateRouteOffer2() {
		authenticate("user1");
		
		RouteOffer result;
		Route route;
		Collection<RouteOffer> routeOffers;
		int sizePreCreate, sizePostCreate;
		
		route = routeService.findOne(UtilTest.getIdFromBeanName("route1"));
		routeOffers = routeOfferService.findAllByRouteId(route.getId());
		sizePreCreate = routeOffers.size();
		
		unauthenticate();
		authenticate("user2");
		
		result = routeOfferService.create(route.getId());
		result.setAmount(1);
		result.setDescription("Test");
		result.setAcceptedByCarrier(true);
		result = routeOfferService.save(result);
		
		unauthenticate();
		authenticate("user1");
		
		route = routeService.findOne(UtilTest.getIdFromBeanName("route1"));
		routeOffers = routeOfferService.findAllByRouteId(route.getId());
		sizePostCreate = routeOffers.size();

		Assert.isTrue(result.getAcceptedByCarrier() == false);
		Assert.isTrue(sizePostCreate == sizePreCreate + 1);
		Assert.isTrue(routeOffers.contains(result));
		
		unauthenticate();
	}
	
	/**
	 * @Test Create a Route Offer
	 * @result The user tries to create a route offer with other UserId
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negativeCreateRouteOffer1() {
		authenticate("user1");
		
		RouteOffer result;
		Route route;
		Collection<RouteOffer> routeOffers;
		int sizePreCreate, sizePostCreate;
		User user;
		
		route = routeService.findOne(UtilTest.getIdFromBeanName("route1"));
		routeOffers = routeOfferService.findAllByRouteId(route.getId());
		sizePreCreate = routeOffers.size();
		user = userService.findOne(UtilTest.getIdFromBeanName("user3"));
		
		unauthenticate();
		authenticate("user2");
		
		result = routeOfferService.create(route.getId());
		result.setAmount(1);
		result.setDescription("Test");
		result.setUser(user);
		result = routeOfferService.save(result);
		
		unauthenticate();
		authenticate("user1");
		
		route = routeService.findOne(UtilTest.getIdFromBeanName("route1"));
		routeOffers = routeOfferService.findAllByRouteId(route.getId());
		sizePostCreate = routeOffers.size();

		Assert.isTrue(sizePostCreate == sizePreCreate + 1);
		Assert.isTrue(routeOffers.contains(result));
		
		unauthenticate();
	}
	
	/**
	 * @Test Create a Route Offer
	 * @result The user tries to create an offer on their own route
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negativeCreateRouteOffer2() {
		authenticate("user1");
		
		RouteOffer result;
		Route route;
		Collection<RouteOffer> routeOffers;
		int sizePreCreate, sizePostCreate;
		
		route = routeService.findOne(UtilTest.getIdFromBeanName("route1"));
		routeOffers = routeOfferService.findAllByRouteId(route.getId());
		sizePreCreate = routeOffers.size();		
		
		result = routeOfferService.create(route.getId());
		result.setAmount(1);
		result.setDescription("Test");
		result = routeOfferService.save(result);
		
		route = routeService.findOne(UtilTest.getIdFromBeanName("route1"));
		routeOffers = routeOfferService.findAllByRouteId(route.getId());
		sizePostCreate = routeOffers.size();

		Assert.isTrue(sizePostCreate == sizePreCreate + 1);
		Assert.isTrue(routeOffers.contains(result));
		
		unauthenticate();
	}
	
	/**
	 * @Test Create a Route Offer
	 * @result The user tries to create an offer without being logged in
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negativeCreateRouteOffer3() {
		authenticate("user1");
		
		RouteOffer result;
		Route route;
		Collection<RouteOffer> routeOffers;
		int sizePreCreate, sizePostCreate;
		
		route = routeService.findOne(UtilTest.getIdFromBeanName("route1"));
		routeOffers = routeOfferService.findAllByRouteId(route.getId());
		sizePreCreate = routeOffers.size();
		
		unauthenticate();
		
		result = routeOfferService.create(route.getId());
		result.setAmount(1);
		result.setDescription("Test");
		result = routeOfferService.save(result);
		
		authenticate("user1");
		
		route = routeService.findOne(UtilTest.getIdFromBeanName("route1"));
		routeOffers = routeOfferService.findAllByRouteId(route.getId());
		sizePostCreate = routeOffers.size();

		Assert.isTrue(sizePostCreate == sizePreCreate + 1);
		Assert.isTrue(routeOffers.contains(result));
		
		unauthenticate();
	}
	
	/**
	 * @Test Accept a Route Offer
	 * @result The user accepts a route offer
	 */
	@Test
	public void positiveAcceptRouteOffer1() {
		authenticate("user1");
		
		Route route;
		RouteOffer ro;
		Collection<RouteOffer> routeOffers;
		
		route = routeService.findOne(UtilTest.getIdFromBeanName("route1"));
		routeOffers = routeOfferService.findAllByRouteId(route.getId());
		ro = routeOffers.iterator().next();
		
		routeOfferService.accept(ro.getId());
		
		ro = routeOfferService.findOne(ro.getId());
		
		Assert.isTrue(ro.getAcceptedByCarrier() == true && ro.getRejectedByCarrier() == false);
		
		unauthenticate();
	}
	
	/**
	 * @Test Accept a Route Offer
	 * @result The user tries to accept their own route offer
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negativeAcceptRouteOffer1() {
		authenticate("user1");
		
		Route route;
		RouteOffer ro;
		Collection<RouteOffer> routeOffers;
		
		route = routeService.findOne(UtilTest.getIdFromBeanName("route1"));
		routeOffers = routeOfferService.findAllByRouteId(route.getId());
		ro = routeOffers.iterator().next();
		
		unauthenticate();
		authenticate("user2");
		
		routeOfferService.accept(ro.getId());
		
		ro = routeOfferService.findOne(ro.getId());
		
		Assert.isTrue(ro.getAcceptedByCarrier() == true && ro.getRejectedByCarrier() == false);
		
		unauthenticate();
	}
	
	/**
	 * @Test Accept a Route Offer
	 * @result The user tries to accept an offer without being logged in
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negativeAcceptRouteOffer2() {
		authenticate("user1");
		
		Route route;
		RouteOffer ro;
		Collection<RouteOffer> routeOffers;
		
		route = routeService.findOne(UtilTest.getIdFromBeanName("route1"));
		routeOffers = routeOfferService.findAllByRouteId(route.getId());
		ro = routeOffers.iterator().next();
		
		unauthenticate();
		
		routeOfferService.accept(ro.getId());
		
		ro = routeOfferService.findOne(ro.getId());
		
		Assert.isTrue(ro.getAcceptedByCarrier() == true && ro.getRejectedByCarrier() == false);
		
	}
	
	/**
	 * @Test Accept a Route Offer
	 * @result The user tries to accept an offer that is rejected
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negativeAcceptRouteOffer3() {
		authenticate("user1");
		
		RouteOffer ro;
		
		ro = routeOfferService.findOne(UtilTest.getIdFromBeanName("routeOffer3"));				
		routeOfferService.accept(ro.getId());
		ro = routeOfferService.findOne(ro.getId());
		
		Assert.isTrue(ro.getAcceptedByCarrier() == true && ro.getRejectedByCarrier() == false);
		
		unauthenticate();
	}
	
	/**
	 * @Test Accept a Route Offer
	 * @result The user tries to accept an offer that is accepted
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negativeAcceptRouteOffer4() {
		authenticate("user1");
		
		RouteOffer ro;
		
		ro = routeOfferService.findOne(UtilTest.getIdFromBeanName("routeOffer4"));				
		routeOfferService.accept(ro.getId());
		ro = routeOfferService.findOne(ro.getId());
		
		Assert.isTrue(ro.getAcceptedByCarrier() == true && ro.getRejectedByCarrier() == false);
		
		unauthenticate();
	}

}