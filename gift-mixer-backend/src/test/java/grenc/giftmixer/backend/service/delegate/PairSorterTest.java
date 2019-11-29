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

import grenc.giftmixer.backend.model.Pair;

public class PairSorterTest {
	
	private PairSorter sorter = new PairSorter();

	@Test
	public void shouldShuffle() {
		List<String> original = Arrays.asList("a", "b", "c", "d", "e");
		List<String> copy = new ArrayList<String>(original);
		Collections.shuffle(copy);
		assertAreNotSameOrdered(original, copy);
	}
	
	@Test
	public void shouldCreatePairs() {
		List<String> original = Arrays.asList("a", "b", "c", "d", "e");
		List<Pair> pairs = sorter.splitIntoPairs(original);
		
		Set<String> setOfReceivers = new HashSet<>();
		Set<String> setOfWishers = new HashSet<>();
		
		for (Pair pair : pairs) {
			assertNotEquals(pair.receiver, pair.wisher);
			setOfReceivers.add(pair.receiver);
			setOfWishers.add(pair.wisher);
		}
		
		assertEquals(original.size(), setOfReceivers.size());
		assertEquals(original.size(), setOfWishers.size());
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
