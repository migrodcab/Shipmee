package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Route extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private Date date;
	private Date departureTime;
	private Date arriveTime;
	private String origin;
	private String destination;
	private String itemEnvelope;
	
	@NotNull
	@DateTimeFormat
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	@NotNull
	@DateTimeFormat
	public Date getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	
	@NotNull
	@DateTimeFormat
	public Date getArriveTime() {
		return arriveTime;
	}
	
	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
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
	
	@NotNull
	@NotBlank
	public String getItemEnvelope() {
		return itemEnvelope;
	}
	
	public void setItemEnvelope(String itemEnvelope) {
		this.itemEnvelope = itemEnvelope;
	}
	
	
	// Relationships ----------------------------------------------------------
	private User user;
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
