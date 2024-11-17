package grenc.giftmixer.backend.service.email.content;

import javax.mail.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class VerificationContentGenerator extends AbstractContentGenerator {

  private static final String subject = "Skrivni Božiček!";

  private static final String templateFile = "email-templates/verification.html";

  public String generateSubject() {
    return subject;
  }

  public String generateContent(String... templateData) throws MessagingException {
    String receiver = templateData[0];
    String verificationLink = templateData[1];
    String organizer = templateData[2];
    String organizerEmail = templateData[3];
    String templateContent = readFileContent(templateFile);
    return String.format(templateContent, receiver, organizer, organizerEmail, verificationLink, organizerEmail);
  }
}
