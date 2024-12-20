package grenc.giftmixer.backend.controller.email;

import grenc.giftmixer.backend.model.chain.Chain;
import grenc.giftmixer.backend.model.chain.ChainService;
import grenc.giftmixer.backend.model.chain.GiverRecieverPair;
import grenc.giftmixer.backend.model.user.admin.Admin;
import grenc.giftmixer.backend.model.user.admin.AdminService;
import grenc.giftmixer.backend.model.user.participant.Participant;
import grenc.giftmixer.backend.model.user.participant.ParticipantService;
import grenc.giftmixer.backend.model.wish.Wish;
import grenc.giftmixer.backend.model.wish.WishService;
import grenc.giftmixer.backend.service.VerificationService;
import grenc.giftmixer.backend.service.email.EmailService;
import grenc.giftmixer.backend.service.email.content.SampleContentGenerator;
import grenc.giftmixer.backend.service.email.content.TargetGiftContentGenerator;
import grenc.giftmixer.backend.service.email.content.VerificationContentGenerator;
import grenc.giftmixer.backend.service.email.content.WishInvitationContentGenerator;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

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

  @Autowired
  private ChainService chainService;
  @Autowired
  private WishService wishService;

  @Autowired
  private AdminService adminService;
  @Autowired
  private ParticipantService participantService;

  @Autowired
  private EmailService emailService;

  @Value(value = "${host.address.wish}")
  private String wishAddress;

  @RequestMapping(value = "/sendEmail_sample", method = RequestMethod.POST)
  public void sampleEmail(@RequestBody Long participantId) {
		Participant participant = participantService.participantById(participantId);
		if (participant == null) {
			System.out.println("Cannot determine participant with id: " + participantId);
			return;
		}

    try {
      String subject = sampleContentGenerator.generateSubject();
      String content = sampleContentGenerator.generateContent();
      emailService.sendEmail(participant.getEmail(), subject, content);

    } catch (MessagingException e) {
      System.out.println("Failed to send sample message for: " + participant.getEmail());
      e.printStackTrace();
    }
  }

  @RequestMapping(value = "/sendEmail_verification", method = RequestMethod.POST)
  public int sendVerification(@RequestBody List<Long> participantIds) {
    Admin admin = adminService.currentAdmin();
    String subject = verificationContentGenerator.generateSubject();
    int successfulMessages = 0;

    for (Long participantId : participantIds) {
      Participant participant = participantService.participantById(participantId);
      String verificationLink = verificationService.verificationLink(participant);

      try {
        String content = verificationContentGenerator.generateContent(
            participant.getName(), verificationLink,
            admin.getUsername(), admin.getEmail());

        emailService.sendEmail(participant.getEmail(), subject, content);

        successfulMessages++;
        participant.setSentConfirmationEmail(true);
        participant = participantService.editParticipant(participant);

      } catch (MessagingException e) {
        System.out.println("Failed to send message for: " + participant.getEmail());
        e.printStackTrace();
      }
    }
    return successfulMessages;
  }

  @RequestMapping(value = "/sendEmail_wishInvitation", method = RequestMethod.POST)
  public int wishInvitation(@RequestBody List<Long> participantIds) {
    Admin admin = adminService.currentAdmin();
    String subject = wishInvitationContentGenerator.generateSubject();
    int successfulMessages = 0;

    for (Long participantId : participantIds) {
      Participant participant = participantService.participantById(participantId);
      String wishLink = wishAddress + "/" + participant.getCode();

      try {
        String content = wishInvitationContentGenerator.generateContent(
            participant.getName(), wishLink,
            admin.getUsername(), admin.getEmail());

        emailService.sendEmail(participant.getEmail(), subject, content);

        successfulMessages++;
        participant.setSentWishLink(true);
        participant = participantService.editParticipant(participant);

      } catch (MessagingException e) {
        System.out.println("Failed to send message for: " + participant.getEmail());
        e.printStackTrace();
      }
    }
    return successfulMessages;
  }

  @RequestMapping(value = "/sendEmail_targetGift", method = RequestMethod.POST)
  public int targetGift(@RequestBody List<Long> participantIds) {
    Admin admin = adminService.currentAdmin();
    String subject = targetGiftContentGenerator.generateSubject();
    int successfulMessages = 0;

    Chain chain = chainService.findChain(admin.getId());
    for (GiverRecieverPair pair : chain.getPairs()) {
      for (Long participantId : participantIds) {
        if (participantId == pair.getGiverId()) {

          Participant giver = participantService.participantById(participantId);
          Participant receiver = participantService.participantById(pair.getReceiverId());

          Wish targetWish = wishService.fetchWish(pair.getReceiverId());
          String wishContent = (targetWish != null) ? targetWish.getWishContent() : null;

          try {
            String content = targetGiftContentGenerator.generateContent(receiver.getName(), wishContent);

            emailService.sendEmail(giver.getEmail(), subject, content);

            successfulMessages++;
            giver.setSentTargetGiftMessage(true);
            giver = participantService.editParticipant(giver);

          } catch (MessagingException e) {
            System.out.println("Failed to send message for: " + giver.getEmail());
            e.printStackTrace();
          }
        }
      }
    }
    return successfulMessages;
  }
}
