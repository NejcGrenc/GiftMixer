package grenc.giftmixer.backend.service.email.content;

import javax.mail.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class WishInvitationContentGenerator extends AbstractContentGenerator {

	private static final String subject = "Skrivni Božiček! " + santaEmoji;
	private static final String templateFile = "email-templates/wish-invitation.html";

	public String generateSubject() {
		return subject;
	}

	public String generateContent(String... templateData) throws MessagingException {
		String receiver = templateData[0];
		String linkToWish = templateData[1];
		String organizer = templateData[2];
		String organizerEmail = templateData[3];
		String templateContent = readFileContent(templateFile);
		return String.format(templateContent, receiver, linkToWish, linkToWish, organizer, organizerEmail);
	}
}
