package grenc.giftmixer.backend.model.user.participant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Participant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column(nullable = false)
    private long adminId;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column
	private boolean sentConfirmationEmail;
	@Column
	private boolean confirmedConfirmationEmail;
	@Column
	private boolean sentWishLink;
	@Column
	private boolean confirmedRecievedWishLink;
	@Column
	private boolean wishMessageWritten;
	@Column
	private boolean sentTargetGiftMessage;
	@Column
	private boolean confirmedRecievedTargetGiftMessage;
	
	@Column
	private String code;

}
