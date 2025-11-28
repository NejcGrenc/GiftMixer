package grenc.giftmixer.backend.service.delegate;

import grenc.giftmixer.backend.model.chain.ChainRule;
import grenc.giftmixer.backend.model.chain.GiverRecieverPair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PairSorter {

	public List<GiverRecieverPair> splitIntoPairs(List<Long> listOfUsers, List<ChainRule> chainRules) {
		List<GiverRecieverPair> pairs;
		int attemptLimit = 10000;
		do {
			pairs = new ArrayList<>();
			List<Long> shuffeledList = new ArrayList<>(listOfUsers);
			Collections.shuffle(shuffeledList);

			for (int i = 0; i < shuffeledList.size(); i++) {
				GiverRecieverPair pair = new GiverRecieverPair();
				pair.setGiverId(shuffeledList.get(i));
				pair.setReceiverId(shuffeledList.get((i+1) % shuffeledList.size()));
				pairs.add(pair);
			}
			attemptLimit--;

		} while (attemptLimit > 0 && !verifyPairs(pairs, chainRules));

		if (attemptLimit == 0) {
			throw new RuntimeException("Nemorem ustvariti veljavnih parov pri teh omejitvah");
		}

		return pairs;
	}

	private boolean verifyPairs(List<GiverRecieverPair> pairs, List<ChainRule> chainRules) {
		for (GiverRecieverPair pair : pairs) {
			for (ChainRule chainRule : chainRules) {
				if (pair.getGiverId() == chainRule.getGiverId() && pair.getReceiverId() == chainRule.getReceiverId()) {
					return false;
				}
			}
		}
		return true;
	}
}
