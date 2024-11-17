package grenc.giftmixer.backend.controller.email;

import grenc.giftmixer.backend.service.VerificationService;
import grenc.giftmixer.backend.service.email.content.SampleContentGenerator;
import grenc.giftmixer.backend.service.email.content.TargetGiftContentGenerator;
import grenc.giftmixer.backend.service.email.content.VerificationContentGenerator;
import grenc.giftmixer.backend.service.email.content.WishInvitationContentGenerator;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateController {

  @Autowired
  private SampleContentGenerator sampleContentGenerator;
  @Autowired
  private VerificationContentGenerator verificationContentGenerator;
  @Autowired
  private WishInvitationContentGenerator wishInvitationContentGenerator;
  @Autowired
  private TargetGiftContentGenerator targetGiftContentGenerator;

  @Autowired
  private VerificationService verificationService;

  @Value(value = "${host.address.wish}")
  private String wishAddress;

  @Value(value = "${template-data.admin}")
  private String admin;
  @Value(value = "${template-data.admin-email}")
  private String adminEmail;
  @Value(value = "${template-data.giver}")
  private String giver;
  @Value(value = "${template-data.giver-email}")
  private String giverEmail;
  @Value(value = "${template-data.receiver}")
  private String receiver;
  @Value(value = "${template-data.receiver-email}")
  private String receiverEmail;
  @Value(value = "${template-data.wish}")
  private String wish;

  @RequestMapping(value = "/template_sample", method = RequestMethod.GET)
  public TemplateResponse sample() {
    try {
      String subject = sampleContentGenerator.generateSubject();
      String content = sampleContentGenerator.generateContent();
      return new TemplateResponse(subject, content);
    } catch (MessagingException e) {
      System.out.println("Failed to get sample message");
      e.printStackTrace();
      return new TemplateResponse();
    }
  }

  @RequestMapping(value = "/template_verification", method = RequestMethod.GET)
  public TemplateResponse verification() {
    try {
      String subject = verificationContentGenerator.generateSubject();
      String verificationLink = verificationService.verificationLink("A1B2C3D4");
      String content = verificationContentGenerator.generateContent(
          receiver, verificationLink,
          admin, adminEmail);
      return new TemplateResponse(subject, content);
    } catch (MessagingException e) {
      System.out.println("Failed to get verification message");
      e.printStackTrace();
      return new TemplateResponse();
    }
  }

  @RequestMapping(value = "/template_wishInvitation", method = RequestMethod.GET)
  public TemplateResponse wishInvitation() {
    try {
      String subject = wishInvitationContentGenerator.generateSubject();
      String wishLink = wishAddress + "/" + "A1B2C3D4";
      String content = wishInvitationContentGenerator.generateContent(
          receiver, wishLink,
          admin, adminEmail);
      return new TemplateResponse(subject, content);
    } catch (MessagingException e) {
      System.out.println("Failed to get verification message");
      e.printStackTrace();
      return new TemplateResponse();
    }
  }

  @RequestMapping(value = "/template_targetGift", method = RequestMethod.GET)
  public TemplateResponse targetGift() {
    try {
      String subject = targetGiftContentGenerator.generateSubject();
      String content = targetGiftContentGenerator.generateContent(receiver, wish);
      return new TemplateResponse(subject, content);
    } catch (MessagingException e) {
      System.out.println("Failed to get verification message");
      e.printStackTrace();
      return new TemplateResponse();
    }
  }
}
