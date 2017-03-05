package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Shipment extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private Date date;
	private Date departureTime;
	private Date maximunArriveTime;
	private String origin;
	private String destination;
	private double price;
	private String itemSize;
	private String itemEnvelope;
	
	@DateTimeFormat
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	@DateTimeFormat
	public Date getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	
	@DateTimeFormat
	public Date getMaximunArriveTime() {
		return maximunArriveTime;
	}
	
	public void setMaximunArriveTime(Date maximunArriveTime) {
		this.maximunArriveTime = maximunArriveTime;
	}
	
	@NotNull
	@NotBlank
	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	@NotNull
	@NotBlank
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	@Min(0)
	@Digits(integer=9,fraction=2)
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	@NotNull
	@NotBlank
	public String getItemSize() {
		return itemSize;
	}
	
	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}
	
	@NotNull
	@NotBlank
	public String getItemEnvelope() {
		return itemEnvelope;
	}
	
	public void setItemEnvelope(String itemEnvelope) {
		this.itemEnvelope = itemEnvelope;
	}
	
	// Relationships ----------------------------------------------------------
	private User creator;
	private User carried;
	
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional=true)
	public User getCarried() {
		return carried;
	}
	public void setCarried(User carried) {
		this.carried = carried;
	}
}
