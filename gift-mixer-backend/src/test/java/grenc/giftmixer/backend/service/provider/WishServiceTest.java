package grenc.giftmixer.backend.service.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import grenc.giftmixer.backend.controller.wish.WishController;
//import grenc.giftmixer.backend.service.delegate.WishFiles;

public class WishServiceTest {
	
	private WishController wishService = new WishController();
//
//	@Before
//	public void setup() throws IOException {
//		wishService.wishFiles = new WishFiles();
//		wishService.wishFiles.wishPath = new ClassPathResource("grenc/giftmixer/backend/service/wishes").getFile().getAbsolutePath();
//	}
//	
//	@Test
//	public void shouldReadExistingWish() {
//		RestResponse<String> response = wishService.fetchWish("samo");
//		assertEquals(true, response.success);
//		assertEquals("A sample brother\n", response.value);
//	}
//	
//	@Test
//	public void shouldReportFailOnNonexistingWish() {
//		RestResponse<String> response = wishService.fetchWish("missing");
//		assertEquals(false, response.success);
//		assertEquals(null, response.value);
//	}
//	
//	@Test
//	public void shouldSaveWish() {
//		String user = "Maximilian";
//		
//		// should save a new Wish
//		WishRequest request = new WishRequest();
//		request.userName = user;
//		request.wishContent = "I like fish!";
//		wishService.saveWish(request);
//		
//		RestResponse<String> response = wishService.fetchWish(user);
//		assertEquals(true, response.success);
//		assertEquals("I like fish!\n", response.value);
//		
//		// should overwrite a Wish
//		WishRequest requestToOvewrite = new WishRequest();
//		requestToOvewrite.userName = user;
//		requestToOvewrite.wishContent = "I do not like fish!";
//		wishService.saveWish(requestToOvewrite);
//		
//		RestResponse<String> overwritenResponse = wishService.fetchWish(user);
//		assertEquals(true, overwritenResponse.success);
//		assertEquals("I do not like fish!\n", overwritenResponse.value);
//		
//		// cleanup
//		boolean deleted = new File(wishService.wishFiles.wishPath + File.separator + user + ".txt").delete();
//		assertTrue(deleted);
//	}
//
//	@Test
//	public void shouldGetWishLists() throws IOException {
//		wishService.wishFiles.wishPath = new ClassPathResource("grenc/giftmixer/backend/service/wishes").getFile().getAbsolutePath();
//				
//		RestResponse<List<String>> response = wishService.wishList();
//		assertTrue(response.success);
//		assertTrue(response.value.size() == 2);
//		assertTrue(response.value.contains("samo"));
//		assertTrue(response.value.contains("uros"));
//	}
//	
//	@Test
//	public void shouldFailWhenRetrievingWishes() throws IOException {
//		wishService.wishFiles.wishPath = "grenc/giftmixer/backend/service/wishesNot";
//				
//		RestResponse<List<String>> response = wishService.wishList();
//		assertFalse(response.success);
//		assertTrue(response.value == null);
//	}
}
