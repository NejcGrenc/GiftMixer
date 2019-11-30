package grenc.giftmixer.backend.service.provider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import grenc.giftmixer.backend.service.delegate.EmailContentGenerator;

public class EmailServiceTest {

	private EmailService emailService = new EmailService();
	
	@Before
	public void setup() {
		emailService.emailContentGenerator = Mockito.mock(EmailContentGenerator.class);
		emailService.javaMailSender = Mockito.mock(JavaMailSender.class);
	}
	
	@Test
	public void noMatterWhatTheEmailIsSent() {
		emailService.sendSingleWish("receiverEmail", "wishHolder", "wishContent");
		Mockito.verify(emailService.javaMailSender).send(Mockito.any(SimpleMailMessage.class));
	}
}