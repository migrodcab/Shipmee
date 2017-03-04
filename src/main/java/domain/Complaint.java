package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Complaint extends DomainEntity {

	// Constructors -----------------------------------------------------------

	// Attributes -------------------------------------------------------------

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
