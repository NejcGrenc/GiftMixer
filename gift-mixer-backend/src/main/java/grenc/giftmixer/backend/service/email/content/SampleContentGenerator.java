package grenc.giftmixer.backend.service.email.content;

import javax.mail.MessagingException;
import org.springframework.stereotype.Component;


@Component
public class SampleContentGenerator extends AbstractContentGenerator {

  private static final String subject = "Testiranje Skrivnega Božička! " + santaEmoji;
  private static final String templateFile = "email-templates/sample.html";

  public String generateSubject() {
    return subject;
  }

  public String generateContent(String... templateData) throws MessagingException {
    return readFileContent(templateFile);
  }
}
