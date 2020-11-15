package grenc.giftmixer.backend.service.delegate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import grenc.giftmixer.backend.model.chain.GiverRecieverPair;

@Component
public class PairSorter {

	public List<GiverRecieverPair> splitIntoPairs(List<Long> listOfUsers) {
		List<Long> shuffeledList = new ArrayList<>(listOfUsers);
		Collections.shuffle(shuffeledList);
		
		List<GiverRecieverPair> pairs = new ArrayList<>();
		for (int i = 0; i < shuffeledList.size(); i++) {
			GiverRecieverPair pair = new GiverRecieverPair();
			pair.setGiverId(shuffeledList.get(i));
			pair.setReceiverId(shuffeledList.get((i+1) % shuffeledList.size()));
			pairs.add(pair);
		}
		return pairs;
	}
}
