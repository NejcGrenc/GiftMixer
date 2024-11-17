package grenc.giftmixer.backend.service.email;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

  @Value(value = "${spring.mail.username}")
  private String senderEmail;

  @Autowired
  private JavaMailSender javaMailSender;

  public Boolean sendEmail(String receiverEmail, String subject, String rawContent) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");

    msgHelper.setTo(receiverEmail);
    msgHelper.setFrom(senderEmail);  /* Sender must be set, otherwise the email will silently fail */
    msgHelper.setSubject(subject);
    log.info("----------");
    log.info("Sending email \"" + subject + "\" to " + receiverEmail);

    ProcessedWithImages processedResult = preprocess(rawContent);
    msgHelper.setText(processedResult.processedHtml, true); /* Text must be set before the image content */
    log.info("Content: " + processedResult.processedHtml);

    for (Pair<String, DataSource> image : processedResult.images) {
      msgHelper.addInline(image.getFirst(), image.getSecond());
      log.info("Images: " + image.getFirst() + " : " + image.getSecond());
    }
    log.info("----------");

    javaMailSender.send(mimeMessage);
    return true;
  }

  private static String readFileContent(String fileName) throws MessagingException {
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

  private static ProcessedWithImages preprocess(String htmlContent) throws MessagingException {
    // Regex pattern to find base64-encoded images in <img> tags
    Pattern pattern = Pattern.compile("<img\\s+[^>]*src\\s*=\\s*\"data:image/(\\w+);base64,([^\"]+)\"[^>]*>");
    Matcher matcher = pattern.matcher(htmlContent);

    StringBuilder processedHtml = new StringBuilder();
    List<Pair<String, DataSource>> images = new ArrayList<>();

    while (matcher.find()) {
      String imageType = matcher.group(1);   // Extract image type (e.g., png, jpeg)
      String base64Image = matcher.group(2); // Extract base64 content

      // Decode base64 and write to DataSource file
      String contentId = UUID.randomUUID().toString();
      String fileNamePrefix = "image-" + contentId;

      File imageFile;
      try {
        imageFile = File.createTempFile(fileNamePrefix, "." + imageType);
        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
          byte[] imageBytes = java.util.Base64.getDecoder().decode(base64Image);
          fos.write(imageBytes);
        }
      } catch (IOException e) {
        throw new MessagingException(e.getMessage());
      }
      DataSource dataSource = new FileDataSource(imageFile);
      images.add(Pair.of(contentId, dataSource));

      // Replace the <img> tag's src with the CID reference
      String replacement = "<img src=\"cid:" + contentId + "\">";
      matcher.appendReplacement(processedHtml, replacement);
    }

    // Append the remaining HTML content
    matcher.appendTail(processedHtml);

    return new ProcessedWithImages(processedHtml.toString(), images);
  }

  @Data
  @AllArgsConstructor
  static class ProcessedWithImages {
    public String processedHtml;
    public List<Pair<String, DataSource>> images = new ArrayList<>();
  }
}
