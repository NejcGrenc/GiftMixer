package grenc.giftmixer.backend.service.provider;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;

import grenc.giftmixer.backend.service.email.content.TargetGiftContentGenerator;

public class EmailServiceTest {

//	private EmailService emailService = new EmailService();
//	
//	private MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);
	
//	@Before
//	public void setup() {
//		emailService.emailContentGenerator = new TargetGiftContentGenerator();
//		emailService.javaMailSender = Mockito.mock(JavaMailSender.class);
//		
//		Mockito.when(emailService.javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
//	}
//	
//	@Test
//	public void noMatterWhatTheEmailIsSent() throws MessagingException {
//		emailService.sendSingleWish("receiverEmail", "wishHolder", "wishContent");
//		Mockito.verify(emailService.javaMailSender).send(mimeMessage);
//	}
//	
//	@Test
//	public void noMatterWhatTheInvitationIsSent() throws MessagingException {
//		emailService.sendInvitation("receiverEmail", "content");
//		Mockito.verify(emailService.javaMailSender).send(mimeMessage);
//	}
}
