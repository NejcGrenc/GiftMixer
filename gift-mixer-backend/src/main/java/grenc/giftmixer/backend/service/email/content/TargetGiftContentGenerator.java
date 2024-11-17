package grenc.giftmixer.backend.service.email.content;

import javax.mail.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class TargetGiftContentGenerator extends AbstractContentGenerator {

	private static final String subject = "Skrivni Božiček! " + santaEmoji;
	private static final String templateFile = "email-templates/target-gift.html";

	public String generateSubject() {
		return subject;
	}
	
	public String generateContent(String... templateData) throws MessagingException  {
		String receiver = templateData[0];
		String wishContent = templateData[1];
		String templateContent = readFileContent(templateFile);
		return String.format(templateContent, receiver, wishContent, receiver);
	}
}
