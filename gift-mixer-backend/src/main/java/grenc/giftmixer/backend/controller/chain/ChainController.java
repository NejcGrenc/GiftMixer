package grenc.giftmixer.backend.controller.chain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.model.chain.Chain;
import grenc.giftmixer.backend.model.chain.ChainService;
import grenc.giftmixer.backend.model.chain.GiverRecieverPair;
import grenc.giftmixer.backend.model.user.admin.Admin;
import grenc.giftmixer.backend.model.user.admin.AdminService;
import grenc.giftmixer.backend.model.user.participant.Participant;
import grenc.giftmixer.backend.model.user.participant.ParticipantService;
import grenc.giftmixer.backend.service.delegate.PairSorter;

@RestController
public class ChainController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ParticipantService participantService;
	
	@Autowired
	private ChainService chainService;
	
	@Autowired
	private PairSorter pairSorter;
	
	@RequestMapping(value = "/chain", method = RequestMethod.POST)
    public Chain chain() {
    	System.out.println("Processing '/chain' request");
    	
    	Admin admin = adminService.currentAdmin();

    	List<Participant> listOfUsers = participantService.participants(admin);
    	System.out.println("Found " + listOfUsers.size() + " participants.");

    	List<Long> listOfUserIds = listOfUsers.stream().map(Participant::getId).collect(Collectors.toList());
    	List<GiverRecieverPair> pairs = pairSorter.splitIntoPairs(listOfUserIds);
    	
    	System.out.println("Created " + pairs.size() + " pairs.");

    	
    	Chain chain = chainService.makeChain(admin.getId(), pairs);
    	return chain;
	}
}
