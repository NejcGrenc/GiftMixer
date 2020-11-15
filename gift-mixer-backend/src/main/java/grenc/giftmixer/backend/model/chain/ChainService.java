package grenc.giftmixer.backend.model.chain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChainService {
	
	@Autowired
	private ChainRepository chainRepository;
	
	@Autowired
	private GiverRecieverPairRepository giverRecieverPairRepository;
	
	public Chain findChain(long adminId) {
		List<Chain> chains = chainRepository.findByAdminId(adminId);
		if (chains == null || chains.isEmpty()) {
			System.out.println("No chain found for adminId: " + adminId);
			return null;
		}
		if (chains.size() > 1) {
			System.out.println("Too many chains found for adminId: " + adminId + ". Returning the first one.");
		}
		return chains.get(0);
	}
	
	public Chain makeChain(long adminId, List<GiverRecieverPair> pairs) {
		Chain chain = new Chain();
		chain.setAdminId(adminId);
		chain = chainRepository.save(chain);
		
		for (GiverRecieverPair pair : pairs) {
			pair.setChainId(chain.getId());
			giverRecieverPairRepository.save(pair);
			
			chain.getPairs().add(pair);
		}

		chain = chainRepository.save(chain);
		return chain;
	}
}
