package grenc.giftmixer.backend.service.provider;

import java.util.Arrays;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import grenc.giftmixer.backend.model.chain.GiverRecieverPair;
import grenc.giftmixer.backend.service.delegate.PairSorter;
import grenc.giftmixer.backend.service.delegate.WishFiles;

public class WishersReceiversDistributorTest {

	private WishersReceiversDistributor distributor = new WishersReceiversDistributor();
	
//	@Before
//	public void setup() {
//		distributor.wishFiles = Mockito.mock(WishFiles.class);
//		distributor.wishService = Mockito.mock(WishService.class);
//		distributor.pairSorter = Mockito.mock(PairSorter.class);
//		distributor.userService = Mockito.mock(UserService.class);
//		distributor.emailService = Mockito.mock(EmailService.class);
//		distributor.hostAddressDarilo = "localhost";
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test
//	public void shouldSendEmailsToEveryone() throws MessagingException {
//		Mockito.when(distributor.wishFiles.findAllWishFiles()).thenReturn(Arrays.asList("a", "b", "c", "d", "e"));
//		Mockito.when(distributor.pairSorter.splitIntoPairs(Mockito.anyList()))
//				.thenReturn(Arrays.asList(new GiverRecieverPair("a", "c"), new GiverRecieverPair("b", "d"), new GiverRecieverPair("c", "e"), new GiverRecieverPair("d", "a"), new GiverRecieverPair("e", "b")));
//		Mockito.when(distributor.userService.fetchEmailForUser(Mockito.anyString())).thenReturn("email");
//		Mockito.when(distributor.wishService.fetchWish(Mockito.anyString(), Mockito.anyString())).thenReturn(RestResponse.success("wish"));
//
//		distributor.distributeWishes();
//		
//		Mockito.verify(distributor.emailService, Mockito.times(5)).sendSingleWish(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test
//	public void shouldSendInvitationsToEveryone() throws MessagingException {
//		Mockito.when(distributor.pairSorter.splitIntoPairs(Mockito.anyList()))
//				.thenReturn(Arrays.asList(new GiverRecieverPair("a", "c"), new GiverRecieverPair("b", "d"), new GiverRecieverPair("c", "e"), new GiverRecieverPair("d", "a"), new GiverRecieverPair("e", "b")));
//		Mockito.when(distributor.userService.users()).thenReturn(RestResponse.success(Arrays.asList("a", "b", "c", "d", "e")));
//		Mockito.when(distributor.userService.fetchEmailForUser(Mockito.anyString())).thenReturn("email");
//		Mockito.when(distributor.userService.fetchCredentialsForUser(Mockito.anyString())).thenReturn("cred");
//
//		distributor.distributeInvitations();
//		
//		Mockito.verify(distributor.emailService, Mockito.times(5)).sendInvitation(Mockito.anyString(), Mockito.anyString());
//	}
	
}
