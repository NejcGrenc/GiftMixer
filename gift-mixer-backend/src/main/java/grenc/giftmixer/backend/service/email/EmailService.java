package grenc.giftmixer.backend.service.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class EmailService {

  @Value(value = "${spring.mail.username}")
  private String senderEmail;

  @Autowired
  private JavaMailSender javaMailSender;

  public Boolean sample(String receiverEmail) {
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setTo(receiverEmail);

    msg.setSubject("Testing from Spring Boot");
    msg.setText("Hello World \n Spring Boot Email");
    /* Sender must be set, otherwise the email will silently fail */
    msg.setFrom(senderEmail);

    javaMailSender.send(msg);
    return true;
  }

  public Boolean sendEmail(String receiverEmail, String subject, String content) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

    helper.setTo(receiverEmail);
    helper.setSubject(subject);
    helper.setText(content, true);

    javaMailSender.send(mimeMessage);
    return true;
  }

}
