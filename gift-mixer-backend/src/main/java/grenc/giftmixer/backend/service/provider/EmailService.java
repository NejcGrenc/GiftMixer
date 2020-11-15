package grenc.giftmixer.backend.service.provider;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.service.delegate.email.content.TargetGiftContentGenerator;

@RestController
public class EmailService {
	
	@Autowired
	TargetGiftContentGenerator emailContentGenerator;

	@Autowired
    JavaMailSender javaMailSender;
	
    @RequestMapping(value = "/emailTest", method = RequestMethod.POST)
	public Boolean sample() {
    	  SimpleMailMessage msg = new SimpleMailMessage();
          msg.setTo("nejc.grenc@gmail.com");

          msg.setSubject("Testing from Spring Boot");
          msg.setText("Hello World \n Spring Boot Email");

          javaMailSender.send(msg);
          return true;
    }
    
    public Boolean sendInvitation(String receiverEmail, String content) throws MessagingException {
    	MimeMessage mimeMessage = javaMailSender.createMimeMessage();    	
    	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

    	helper.setTo(receiverEmail);
    	helper.setSubject(emailContentGenerator.generateSubject());
    	helper.setText(content, true);

    	javaMailSender.send(mimeMessage);
        return true;
    }
    
    public Boolean sendSingleWish(String receiverEmail, String wishHolder, String wishContent) throws MessagingException {
    	MimeMessage mimeMessage = javaMailSender.createMimeMessage();    	
    	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

    	helper.setTo(receiverEmail);
    	helper.setSubject(emailContentGenerator.generateSubject());
    	helper.setText(emailContentGenerator.generateContent(wishHolder, wishContent), true);

    	javaMailSender.send(mimeMessage);
        return true;
    }
}
