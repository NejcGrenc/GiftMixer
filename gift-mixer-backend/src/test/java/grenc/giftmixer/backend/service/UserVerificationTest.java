package grenc.giftmixer.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

public class UserVerificationTest {

	private UserVerification userVerification = new UserVerification();
	
	@Test
	public void testValidVerificationMap() throws IOException {
		userVerification.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-ok.txt").getFile().getAbsolutePath();

		Map<String, String> map = userVerification.verificationMap();
		assertEquals(2, map.size());
		assertTrue(map.containsKey("Nejc"));
		assertTrue(map.containsKey("Ines"));
	}
	
	@Test
	public void testInValidVerificationMap() throws IOException {
		userVerification.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-bad.txt").getFile().getAbsolutePath();

		Map<String, String> map = userVerification.verificationMap();
		assertEquals(0, map.size());
	}
	
	@Test
	public void shouldVerifyUser() throws IOException {
		userVerification.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-ok.txt").getFile().getAbsolutePath();

		assertTrue(userVerification.verifyUser(request("Nejc", "12345")));
	}
	
	@Test
	public void shouldNotVerifyUser() throws IOException {
		userVerification.usersFile = new ClassPathResource("grenc/giftmixer/backend/service/users-ok.txt").getFile().getAbsolutePath();

		assertFalse(userVerification.verifyUser(request("Nejc", "12346")));
	}
	
	private UserVerificationRequest request(String user, String code) {
		UserVerificationRequest request = new UserVerificationRequest();
		request.userName = user;
		request.userCode = code;
		return request;
	}

}
