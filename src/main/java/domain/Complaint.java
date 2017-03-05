package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Complaint extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String explanation;
	
	@NotNull
	@NotBlank
	public String getExplanation() {
		return explanation;
	}
	
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	// Relationships ----------------------------------------------------------
	private Moderator moderator;
	private User creator;
	private User involved;
	
	@Valid
	@ManyToOne(optional=true)
	public Moderator getModerator() {
		return moderator;
	}
	public void setModerator(Moderator moderator) {
		this.moderator = moderator;
	}
	
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
	@ManyToOne(optional=false)
	public User getInvolved() {
		return involved;
	}
	public void setInvolved(User involved) {
		this.involved = involved;
	}
	
	
	
}