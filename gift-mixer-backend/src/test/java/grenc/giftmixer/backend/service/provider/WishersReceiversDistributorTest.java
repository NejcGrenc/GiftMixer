package grenc.giftmixer.backend.service.provider;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import grenc.giftmixer.backend.model.Pair;
import grenc.giftmixer.backend.model.rest.RestResponse;
import grenc.giftmixer.backend.service.delegate.PairSorter;
import grenc.giftmixer.backend.service.delegate.WishFiles;

public class WishersReceiversDistributorTest {

	private WishersReceiversDistributor distributor = new WishersReceiversDistributor();
	
	@Before
	public void setup() {
		distributor.wishFiles = Mockito.mock(WishFiles.class);
		distributor.wishService = Mockito.mock(WishService.class);
		distributor.pairSorter = Mockito.mock(PairSorter.class);
		distributor.emailService = Mockito.mock(EmailService.class);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldSendEmailsToEveryone() {
		Mockito.when(distributor.wishFiles.findAllWishFiles()).thenReturn(Arrays.asList("a", "b", "c", "d", "e"));
		Mockito.when(distributor.pairSorter.splitIntoPairs(Mockito.anyList()))
				.thenReturn(Arrays.asList(new Pair("a", "c"), new Pair("b", "d"), new Pair("c", "e"), new Pair("d", "a"), new Pair("e", "b")));
		Mockito.when(distributor.wishService.fetchWish(Mockito.anyString())).thenReturn(RestResponse.success("wish"));

		distributor.distributeWishes();
		
		Mockito.verify(distributor.emailService, Mockito.times(5)).sendSingleWish(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
	}
	
}