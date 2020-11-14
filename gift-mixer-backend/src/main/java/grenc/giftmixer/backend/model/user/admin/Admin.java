package grenc.giftmixer.backend.model.user.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String username;
	
	@Column
	private boolean alreadySentAllEmailValidation;
	@Column
	private boolean alreadySentAllWishLinks;
	@Column
	private boolean alreadySentAllTargetGiftMessages;
	
	@Column
	private String emailValidationTemplate;
	@Column
	private String wishLinkTemplate;
	@Column
	private String targetGiftMessageTemplate;
}


