package grenc.giftmixer.backend.model.chain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ChainRule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private long adminId;

	@Column(nullable = false)
	private String ruleType;

	@Column(nullable = false)
	private long giverId;

	@Column(nullable = false)
	private long receiverId;
}
