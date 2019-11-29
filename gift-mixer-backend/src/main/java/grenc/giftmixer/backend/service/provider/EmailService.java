package grenc.giftmixer.backend.service.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.service.delegate.EmailContentGenerator;

@RestController
public class EmailService {
	
	@Autowired
	EmailContentGenerator emailContentGenerator;

	@Autowired
    JavaMailSender javaMailSender;
	
    @RequestMapping(value = "/emailTest", method = RequestMethod.GET)
	public Boolean sample() {
    	  SimpleMailMessage msg = new SimpleMailMessage();
          msg.setTo("n.grenc@j-it.at");

          msg.setSubject("Testing from Spring Boot");
          msg.setText("Hello World \n Spring Boot Email");

          javaMailSender.send(msg);
          return true;
    }
    
    public Boolean sendSingleWish(String receiverEmail, String wishHolder, String wishContent) {
    	SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(receiverEmail);

        msg.setSubject(emailContentGenerator.generateSubject());
        msg.setText(emailContentGenerator.generateContent(wishHolder, wishContent));

        javaMailSender.send(msg);
        return true;
    }
}
