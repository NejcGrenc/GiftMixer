package grenc.giftmixer.backend.service.delegate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import grenc.giftmixer.backend.model.Pair;

@Component
public class PairSorter {

	public List<Pair> splitIntoPairs(List<String> listOfUsers) {
		List<String> shuffeledList = new ArrayList<>(listOfUsers);
		Collections.shuffle(shuffeledList);
		
		List<Pair> pairs = new ArrayList<>();
		for (int i = 0; i < shuffeledList.size(); i++) {
			pairs.add(new Pair(shuffeledList.get(i), shuffeledList.get((i+1) % shuffeledList.size())));
		}
		return pairs;
	}
}
