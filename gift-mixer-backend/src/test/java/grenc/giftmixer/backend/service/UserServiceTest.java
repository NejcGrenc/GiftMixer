package grenc.giftmixer.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import grenc.giftmixer.backend.model.User;
import grenc.giftmixer.backend.model.UserVerificationRequest;

public class UserServiceTest {
	
	private UserService userService = new UserService();
	
	@Test
	public void shouldVerifyUser() throws IOException {
		userService.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-ok.txt").getFile().getAbsolutePath();
		assertTrue(userService.verifyUser(new UserVerificationRequest("Nejc", "12345")));
	}
	
	@Test
	public void shouldNotVerifyUser() throws IOException {
		userService.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-ok.txt").getFile().getAbsolutePath();
		assertFalse(userService.verifyUser(new UserVerificationRequest("Nejc", "123466")));
	}

	@Test
	public void shouldFindAllUsers() throws IOException {
		userService.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-ok.txt").getFile().getAbsolutePath();

		List<String> users = userService.users();
		assertEquals(2, users.size());
		assertTrue(users.contains("Nejc"));
		assertTrue(users.contains("Ines"));
	}
	
	@Test
	public void testValidMap() throws IOException {
		userService.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-ok.txt").getFile().getAbsolutePath();

		Map<String, User> map = userService.userDataMap();
		assertEquals(2, map.size());
		assertTrue(map.containsKey("Nejc"));
		assertTrue(map.containsKey("Ines"));
		System.out.println(map);
		assertEquals("Nejc", map.get("Nejc").name);
		assertEquals("12345", map.get("Nejc").securityCode);	
		assertEquals("nejc@grenc.si", map.get("Nejc").email);
		assertEquals("Ines", map.get("Ines").name);
		assertEquals("55433", map.get("Ines").securityCode);
		assertEquals("ines@grenc.si", map.get("Ines").email);
	}
	
	@Test(expected = RuntimeException.class)
	public void testInvalidVerificationMap() throws IOException {
		userService.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-invalid.txt").getFile().getAbsolutePath();
		userService.userDataMap();
	}
	
	@Test(expected = RuntimeException.class)
	public void testBadVerificationMap() throws IOException {
		userService.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-bad.txt").getFile().getAbsolutePath();
		userService.userDataMap();
	}
}
