package grenc.giftmixer.backend.service;

import grenc.giftmixer.backend.service.delegate.ParticipantCodeService;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import grenc.giftmixer.backend.model.user.participant.Participant;
import grenc.giftmixer.backend.model.user.participant.ParticipantService;

@Service
public class VerificationService {
	
	@Value(value = "${host.address.verification}")
	private String verificationAddress;
	
	@Autowired
	private ParticipantService participantService;

	public Participant verifyUserByCode(String code) {		
		Participant participant = participantService.participantByCode(code);
		if (participant != null) {
			participant.setConfirmedConfirmationEmail(true);
			participant = participantService.editParticipant(participant);
		}
    	return participant;
	}
	
	public String verificationLink(Participant participant) {
		return verificationAddress + "/" + participant.getCode();
	}

	public String verificationLink(String participantCode) {
		return verificationAddress + "/" + participantCode;
	}
	
}
