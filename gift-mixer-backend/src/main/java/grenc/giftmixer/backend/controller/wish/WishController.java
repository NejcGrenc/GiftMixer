 package grenc.giftmixer.backend.controller.wish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.controller.wish.model.WishRequest;
import grenc.giftmixer.backend.controller.wish.model.WishResponse;
import grenc.giftmixer.backend.model.user.participant.Participant;
import grenc.giftmixer.backend.model.user.participant.ParticipantService;
import grenc.giftmixer.backend.model.wish.Wish;
import grenc.giftmixer.backend.model.wish.WishService;

@RestController
public class WishController {
	
	@Autowired
	private WishService wishService;
	
	@Autowired
	private ParticipantService participantService;
	
    @RequestMapping(value = "/saveWish", method = RequestMethod.POST)
    public void saveWish(@RequestBody WishRequest request) {
    	System.out.println("Processing '/saveWish' request from user: " + request.getParticipantId());
    	
    	if (request.getWishContent() == null || request.getWishContent().isEmpty()) {
    		throw new RuntimeException("No wish present!");
    	}
    	wishService.saveWish(request.getParticipantId(), request.getWishContent());
    	
    	Participant participant = participantService.participantById(request.getParticipantId());
    	if (participant != null) {
    		participant.setConfirmedRecievedWishLink(true);
    		participant.setWishMessageWritten(true);
    		participant = participantService.editParticipant(participant);
    	}
    }
    
    @RequestMapping(value = "/loadWish", method = RequestMethod.POST)
    public WishResponse fetchWish(@RequestBody long participantId) {
    	System.out.println("Processing '/loadWish' request from user: " + participantId);
    	
    	WishResponse response = new WishResponse();
		response.setParticipantId(participantId);
		
    	Wish wish = wishService.fetchWish(participantId);
    	if (wish != null) {
    		response.setWishContent(wish.getWishContent());
    	}
    	
    	Participant participant = participantService.participantById(participantId);
    	if (participant != null) {
    		participant.setConfirmedRecievedWishLink(true);
    		participant = participantService.editParticipant(participant);
    	}

    	return response;
    }
    
}
