package grenc.giftmixer.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import grenc.giftmixer.backend.model.WishListResponse;
import grenc.giftmixer.backend.model.WishRequest;
import grenc.giftmixer.backend.model.WishResponse;
import grenc.giftmixer.backend.service.delegate.WishFiles;

public class WishServiceTest {
	
	private WishService wishService = new WishService();

	@Before
	public void setup() throws IOException {
		wishService.wishFiles = new WishFiles();
		wishService.wishFiles.wishPath = new ClassPathResource("grenc/giftmixer/backend/service/wishes").getFile().getAbsolutePath();
	}
	
	@Test
	public void shouldReadExistingWish() {
		WishResponse response = wishService.fetchWish("samo");
		assertEquals(true, response.success);
		assertEquals("A sample brother\n", response.wishContent);
	}
	
	@Test
	public void shouldReportFailOnNonexistingWish() {
		WishResponse response = wishService.fetchWish("missing");
		assertEquals(false, response.success);
		assertEquals(null, response.wishContent);
	}
	
	@Test
	public void shouldSaveWish() {
		String user = "Maximilian";
		
		// should save a new Wish
		WishRequest request = new WishRequest();
		request.userName = user;
		request.wishContent = "I like fish!";
		wishService.saveWish(request);
		
		WishResponse response = wishService.fetchWish(user);
		assertEquals(true, response.success);
		assertEquals("I like fish!\n", response.wishContent);
		
		// should overwrite a Wish
		WishRequest requestToOvewrite = new WishRequest();
		requestToOvewrite.userName = user;
		requestToOvewrite.wishContent = "I do not like fish!";
		wishService.saveWish(requestToOvewrite);
		
		WishResponse overwritenResponse = wishService.fetchWish(user);
		assertEquals(true, overwritenResponse.success);
		assertEquals("I do not like fish!\n", overwritenResponse.wishContent);
		
		// cleanup
		boolean deleted = new File(wishService.wishFiles.wishPath + File.separator + user + ".txt").delete();
		assertTrue(deleted);
	}

	@Test
	public void shouldGetWishLists() throws IOException {
		wishService.userService = new UserService();
		wishService.userService.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-ok.txt").getFile().getAbsolutePath();
				
		WishListResponse response = wishService.wishList();
		assertTrue(response.success);
		assertTrue(response.allUsers.size() == 2);
		assertTrue(response.allUsers.contains("Nejc"));
		assertTrue(response.allUsers.contains("Ines"));
		assertTrue(response.madeWishes.size() == 2);
		assertTrue(response.madeWishes.contains("samo"));
		assertTrue(response.madeWishes.contains("uros"));
	}
	
	@Test
	public void shouldFailWhenRetrievingWishes() throws IOException {
		wishService.userService = new UserService();
		wishService.userService.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-bad.txt").getFile().getAbsolutePath();
				
		WishListResponse response = wishService.wishList();
		assertFalse(response.success);
		assertTrue(response.allUsers == null);
		assertTrue(response.madeWishes == null);
	}
}
