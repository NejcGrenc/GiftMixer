package grenc.giftmixer.backend.model.user.participant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import grenc.giftmixer.backend.model.user.admin.Admin;

@Component
public class ParticipantService {
	
	@Autowired
	private ParticipantRepository participantRepository;
	
	public List<Participant> participants(Admin admin) {
    	System.out.println("Fetching list of all participants under care of admin " + admin.getUsername());
    	return participantRepository.findByAdminId(admin.getId());
	}
	
	public Participant createParticipant(long adminId, String username, String email) {
    	System.out.println("Creating new participant username: " + username + ", email: " + email);
    	Participant participant = new Participant();
    	participant.setAdminId(adminId);
    	participant.setName(username);
    	participant.setEmail(email);
    	
    	participant.setPersonalCode(generateParticipantCode());
    	
    	return participantRepository.saveAndFlush(participant);
	}
	
	public Participant editParticipant(Participant editParticipant) {
    	System.out.println("Editing participant id: " + editParticipant.getId());
    	Optional<Participant> optionalParticipant = participantRepository.findById(editParticipant.getId());
    	if (! optionalParticipant.isPresent()) {
        	System.out.println("WARN: No participant by id " + editParticipant.getId() + " could be found!");
        	return null;
    	}
//    	Participant participant = optionalParticipant.get();
//    	participant.setAdminId(editParticipant.getAdminId());
//    	participant.setName(editParticipant.getName());
//    	participant.setEmail(editParticipant.getEmail());
//    	participant.setSentConfirmationEmail(editParticipant.isSentConfirmationEmail());
//    	participant.setConfirmedConfirmationEmail(editParticipant.isConfirmedConfirmationEmail());
//    	participant.setSentWishLink(editParticipant.isSentWishLink());
//    	participant.setConfirmedRecievedWishLink(editParticipant.isConfirmedRecievedWishLink());
//    	participant.setWishMessageWritten(editParticipant.isWishMessageWritten());
//    	participant.setSentTargetGiftMessage(editParticipant.isSentTargetGiftMessage());
//    	participant.setConfirmedRecievedTargetGiftMessage(editParticipant.isConfirmedRecievedTargetGiftMessage());
//    	participant.setPersonalCode(editParticipant.getPersonalCode());


    	return participantRepository.saveAndFlush(editParticipant);
	}
	
	public void removeParticipant(long participantId) {
    	System.out.println("Removing participnat with id: " + participantId);
    	Optional<Participant> optionalParticipant = participantRepository.findById(participantId);
    	if (! optionalParticipant.isPresent()) {
        	System.out.println("WARN: No participant by id " + participantId + " could be found!");
        	return;
    	}
    	participantRepository.delete(optionalParticipant.get());
	}
	
	
	private String generateParticipantCode() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}

}
