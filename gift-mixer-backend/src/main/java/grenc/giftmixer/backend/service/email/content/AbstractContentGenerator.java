package grenc.giftmixer.backend.service.email.content;

import grenc.giftmixer.backend.service.email.EmailService;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;

public abstract class AbstractContentGenerator {

  protected final static String santaEmoji = " \uD83C\uDF85";

  abstract String generateSubject();

  abstract String generateContent(String... templateData) throws MessagingException;

  protected String readFileContent(String fileName) throws MessagingException {
    try (InputStream fileStream = EmailService.class.getClassLoader().getResourceAsStream(fileName)) {
      if (fileStream == null) {
        throw new IllegalArgumentException("File not found: " + fileName);
      }
      return new String(fileStream.readAllBytes(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
      throw new MessagingException(e.getMessage());
    }
  }
}
