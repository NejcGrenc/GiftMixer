package grenc.giftmixer.backend.service.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import grenc.giftmixer.backend.model.chain.GiverRecieverPair;

public class PairSorterTest {
	
	private PairSorter sorter = new PairSorter();

	@Test
	public void shouldShuffle() {
		List<Long> original = Arrays.asList(100L, 101L, 102L, 103L, 104L);
		List<Long> copy = new ArrayList<>(original);
		Collections.shuffle(copy);
		assertAreNotSameOrdered(original, copy);
	}
	
	@Test
	public void shouldCreatePairs() {
		List<Long> original = Arrays.asList(100L, 101L, 102L, 103L, 104L);
		List<GiverRecieverPair> pairs = sorter.splitIntoPairs(original);
		
		Set<Long> setOfGivers = new HashSet<>();
		Set<Long> setOfReceivers = new HashSet<>();
		
		for (GiverRecieverPair pair : pairs) {
			assertNotEquals(pair.getGiverId(), pair.getReceiverId());
			setOfGivers.add(pair.getGiverId());
			setOfReceivers.add(pair.getReceiverId());
		}
		
		assertEquals(original.size(), setOfGivers.size());
		assertEquals(original.size(), setOfReceivers.size());
	}
	
	
	private <T> void assertAreNotSameOrdered(List<T> original, List<T> shuffled) {
		int samePositioned = 0;
		for (int i = 0; i < shuffled.size(); i++) {
			T shuffledElement = shuffled.get(i);
			assertTrue(original.contains(shuffledElement));
			if (original.get(i).equals(shuffledElement)) {
				samePositioned++;
			}
		}
		assertTrue(samePositioned < shuffled.size());
	}
}
