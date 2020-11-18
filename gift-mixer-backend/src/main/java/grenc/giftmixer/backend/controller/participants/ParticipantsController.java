package grenc.giftmixer.backend.controller.participants;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.controller.participants.model.NewParticipantRequest;
import grenc.giftmixer.backend.controller.participants.model.ParticipantPrivateDataResponse;
import grenc.giftmixer.backend.model.user.admin.Admin;
import grenc.giftmixer.backend.model.user.admin.AdminService;
import grenc.giftmixer.backend.model.user.participant.Participant;
import grenc.giftmixer.backend.model.user.participant.ParticipantService;
import grenc.giftmixer.backend.service.VerificationService;

@RestController
public class ParticipantsController {
	
	@Autowired
	private VerificationService verificationService;
	
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
	
	
	@RequestMapping(value = "/participantByCode", method = RequestMethod.POST)
    public Participant participantByCode(@RequestBody String participantCode) {
    	System.out.println("Processing '/participantByCode' request: " + participantCode);
    	
    	Participant participant = participantService.participantByCode(participantCode);
		
    	System.out.println("Participant retreved by code: " + participant);
    	return participant;
	}
	
	
	@RequestMapping(value = "/participantPrivateData", method = RequestMethod.POST)
    public ParticipantPrivateDataResponse participantPrivateData(@RequestBody long participantId) {
    	System.out.println("Processing '/participantPrivateData' request: " + participantId);
    	
    	Participant participant = participantService.participantById(participantId);
    	ParticipantPrivateDataResponse response = new ParticipantPrivateDataResponse();
    	response.setCode(participant.getCode());
		
    	System.out.println("/participantPrivateData returned: " + response);
    	return response;
	}
	
	
	@RequestMapping(value = "/verifyUser", method = RequestMethod.POST)
    public void verifyUser(@RequestBody String participantCode) {
    	System.out.println("Processing '/verifyUser' request: " + participantCode);
    	
    	Participant participant = verificationService.verifyUserByCode(participantCode);
		
    	System.out.println("Participant verified: " + participant);
	}
	

}
