package grenc.giftmixer.backend.controller.participants;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.controller.participants.model.NewParticipantRequest;
import grenc.giftmixer.backend.model.user.admin.Admin;
import grenc.giftmixer.backend.model.user.admin.AdminService;
import grenc.giftmixer.backend.model.user.participant.Participant;
import grenc.giftmixer.backend.model.user.participant.ParticipantService;

@RestController
public class ParticipantsController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ParticipantService participantService;
	
	@RequestMapping(value = "/allParticipants", method = RequestMethod.POST)
    public List<Participant> allParticipants() {
    	System.out.println("Processing '/allParticipants' request");

    	Admin admin = adminService.currentAdmin();
		List<Participant> participants = participantService.participants(admin);
		if (participants == null) {
			participants = Collections.emptyList();
		}
    	System.out.println("Found " + participants.size() + " participants.");

    	return participants;
	}
	
	@RequestMapping(value = "/newParticipant", method = RequestMethod.POST)
    public Participant newParticipant(@RequestBody NewParticipantRequest newParticipantRequest) {
    	System.out.println("Processing '/newParticipant' request: " + newParticipantRequest);
    	
    	Admin admin = adminService.currentAdmin();
		Participant newParticipant = participantService.createParticipant(admin.getId(), 
				newParticipantRequest.getName(), newParticipantRequest.getEmail());
		
    	System.out.println("New participant created");
    	return newParticipant;
	}
	
	@RequestMapping(value = "/editParticipant", method = RequestMethod.POST)
    public Participant editParticipant(@RequestBody Participant editParticipant) {
    	System.out.println("Processing '/editParticipant' request: " + editParticipant);
    	
		Participant newParticipant = participantService.editParticipant(editParticipant);
		
    	System.out.println("Participant edited");
    	return newParticipant;
	}
	
	@RequestMapping(value = "/removeParticipant", method = RequestMethod.POST)
    public void removeParticipant(@RequestBody long removeParticipantId) {
    	System.out.println("Processing '/removeParticipant' request: " + removeParticipantId);
    	
		participantService.removeParticipant(removeParticipantId);
		
    	System.out.println("Participant removed");
	}

}
