package grenc.giftmixer.backend.model.chain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Chain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
    private long adminId;
	
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "chainId", referencedColumnName = "id")
	private List<GiverRecieverPair> pairs = new ArrayList<>();
}
