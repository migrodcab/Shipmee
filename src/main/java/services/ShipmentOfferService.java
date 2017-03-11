package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Shipment;
import domain.ShipmentOffer;
import domain.User;
import repositories.ShipmentOfferRepository;

@Service
@Transactional
public class ShipmentOfferService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ShipmentOfferRepository shipmentOfferRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService userService;

	@Autowired
	private ShipmentService shipmentService;

	// Constructors -----------------------------------------------------------

	public ShipmentOfferService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public ShipmentOffer create(int shipmentId) {
		ShipmentOffer res;
		Shipment shipment;

		shipment = shipmentService.findOne(shipmentId);
		Assert.notNull(shipment, "service.shipmentOffer.create.isNullShipment");

		res = new ShipmentOffer();
		res.setShipment(shipment);
		res.setUser(userService.findByPrincipal());

		return res;
	}

	public ShipmentOffer createFromClone(int shipmentOfferId) {
		ShipmentOffer res, act;
		act = this.findOne(shipmentOfferId);
		Assert.notNull(act, "service.shipmentOffer.createFromClone.isNullShipment");

		res = this.create(act.getShipment().getId());
		res.setAmount(act.getAmount());
		res.setDescription(act.getDescription());

		return res;
	}

	public ShipmentOffer save(ShipmentOffer input) {
		User actUser;
		ShipmentOffer tmp;

		Assert.notNull(input, "service.shipmentOffer.save.isNull");

		actUser = userService.findByPrincipal();

		if (actUser.equals(input.getUser())) { // User that create shipment
			if (input.getId() != 0) {
				tmp = this.findOne(input.getId());
				Assert.notNull(tmp, "service.shipmentOffer.save.creator.dontFindID");
				Assert.isTrue(tmp.getUser().equals(actUser), "service.shipmentOffer.save.creator.fake");
				Assert.isTrue(!tmp.getAcceptedBySender() && !tmp.getRejectedBySender(),
						"service.shipmentOffer.save.creator.isRejectedOrAccepted");
			} else {
				tmp = this.create(input.getShipment().getId());
			}

			tmp.setAmount(input.getAmount());
			tmp.setDescription(input.getDescription());
		} else if (actUser.equals(input.getShipment().getCreator())) { // User
																		// that
																		// put
																		// the
																		// offer
			Assert.isTrue(input.getId() != 0, "service.shipmentOffer.save.ProposerCreating"); // The
																								// shipmentCreator
																								// can't

			tmp = this.findOne(input.getId());
			Assert.notNull(tmp, "service.shipmentOffer.save.trans.dontFindID");
			Assert.isTrue(tmp.getShipment().getCreator().equals(actUser), "service.shipmentOffer.save.trans.fake");
			tmp.setAcceptedBySender(input.getAcceptedBySender());
			tmp.setRejectedBySender(input.getRejectedBySender());
		} else {
			Assert.isTrue(false, "service.shipmentOffer.save.userNotPermitted");
			return null;
		}
		Assert.isTrue(!tmp.getUser().equals(tmp.getShipment().getCreator()),
				"service.shipmentOffer.save.equalsCreatorAndProposer");

		tmp = shipmentOfferRepository.save(tmp);

		return tmp;
	}

	public void delete(int shipmentOfferId) {
		ShipmentOffer a;

		a = this.findOne(shipmentOfferId);

		Assert.isTrue(this.checkPermission(a), "service.shipmentOffer.delete.notPermitted");

		shipmentOfferRepository.delete(a);
	}

	public ShipmentOffer findOne(int shipmentOfferId) {
		ShipmentOffer result;
		result = shipmentOfferRepository.findOne(shipmentOfferId);

		if (!this.checkPermission(result)) {
			result = null;
		}

		return result;
	}

	// Other business methods -------------------------------------------------

	public void flush() {
		shipmentOfferRepository.flush();
	}

	public Collection<ShipmentOffer> findAllByShipmentId(int shipmentId) {
		Collection<ShipmentOffer> result;
		User actUser;

		actUser = userService.findByPrincipal();
		result = shipmentOfferRepository.findAllByShipmentId(shipmentId);

		if (!result.isEmpty()) {
			Assert.isTrue(result.iterator().next().getShipment().getCreator().equals(actUser),
					"service.shipmentOffer.delete.notPermitted");
		}

		return result;
	}

	// IDs could be <= 0 to ignore in the find
	public Page<ShipmentOffer> findAllByOrShipmentIdAndOrUserId(int shipmentId, int userId, Pageable page) {
		Page<ShipmentOffer> result;
		User actUser;
		Assert.isTrue(shipmentId + userId > 0,
				"service.shipmentOffer.findAllByOrShipmentIdAndOrUserId.notShipmentOrUser");

		actUser = userService.findByPrincipal();

		result = shipmentOfferRepository.findAllByShipmentIdAndUserId(shipmentId, userId, page);

		if (!result.hasContent()) {
			if (shipmentId > 0 && userId <= 0) {
				Assert.isTrue(result.iterator().next().getShipment().getCreator().equals(actUser),
						"service.shipmentOffer.findAllByOrShipmentIdAndOrUserId.notCreator");
			} else if (userId > 0 && shipmentId <= 0) {
				Assert.isTrue(result.iterator().next().getUser().equals(actUser),
						"service.shipmentOffer.findAllByOrShipmentIdAndOrUserId.notPermittedUser");
			} else if (!checkPermission(result.iterator().next())) {
				Assert.isTrue(false, "service.shipmentOffer.findAllByOrShipmentIdAndOrUserId.notPermitted");
			}
		}

		return result;
	}

	private boolean checkPermission(ShipmentOffer input) {
		User actUser;

		actUser = userService.findByPrincipal(); // Inside check if it's null

		if (input != null) {
			return actUser.equals(input.getUser()) || actUser.equals(input.getShipment().getCreator());
		} else {
			return false;
		}
	}

}
