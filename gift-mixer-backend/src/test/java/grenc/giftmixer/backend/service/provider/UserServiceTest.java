package grenc.giftmixer.backend.service.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;


public class UserServiceTest {
	
//	private UserService userService = new UserService();
//	
//	@Test
//	public void shouldVerifyUser() throws IOException {
//		userService.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-ok.txt").getFile().getAbsolutePath();
//		assertTrue(userService.verifyUser(new UserVerificationRequest("Nejc", "12345")));
//	}
//	
//	@Test
//	public void shouldNotVerifyUser() throws IOException {
//		userService.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-ok.txt").getFile().getAbsolutePath();
//		assertFalse(userService.verifyUser(new UserVerificationRequest("Nejc", "123466")));
//	}
//
//	@Test
//	public void shouldFindAllUsers() throws IOException {
//		userService.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-ok.txt").getFile().getAbsolutePath();
//
//		RestResponse<List<String>> response = userService.users();
//		assertTrue(response.success);
//		assertEquals(2, response.value.size());
//		assertTrue(response.value.contains("Nejc"));
//		assertTrue(response.value.contains("Ines"));
//	}
//	
//	@Test
//	public void shouldFindEmailForUser() throws IOException {
//		userService.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-ok.txt").getFile().getAbsolutePath();
//
//		assertEquals("nejc@grenc.si", userService.fetchEmailForUser("Nejc"));
//	}
//	
//	@Test
//	public void shouldFindCredentialsForUser() throws IOException {
//		userService.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-ok.txt").getFile().getAbsolutePath();
//
//		assertEquals("Nejc-12345", userService.fetchCredentialsForUser("Nejc"));
//	}
//	
//	@Test
//	public void testValidMap() throws IOException {
//		userService.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-ok.txt").getFile().getAbsolutePath();
//
//		Map<String, User> map = userService.userDataMap();
//		assertEquals(2, map.size());
//		assertTrue(map.containsKey("Nejc"));
//		assertTrue(map.containsKey("Ines"));
//		System.out.println(map);
//		assertEquals("Nejc", map.get("Nejc").name);
//		assertEquals("12345", map.get("Nejc").securityCode);	
//		assertEquals("nejc@grenc.si", map.get("Nejc").email);
//		assertEquals("Ines", map.get("Ines").name);
//		assertEquals("55433", map.get("Ines").securityCode);
//		assertEquals("ines@grenc.si", map.get("Ines").email);
//	}
//	
//	@Test(expected = RuntimeException.class)
//	public void testInvalidVerificationMap() throws IOException {
//		userService.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-invalid.txt").getFile().getAbsolutePath();
//		userService.userDataMap();
//	}
//	
//	@Test(expected = RuntimeException.class)
//	public void testBadVerificationMap() throws IOException {
//		userService.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-bad.txt").getFile().getAbsolutePath();
//		userService.userDataMap();
//	}
}
