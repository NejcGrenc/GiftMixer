package grenc.giftmixer.backend.controller.chain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.controller.chain.model.ChainResponse;
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
    public ChainResponse chain() {
    	System.out.println("Processing '/chain' request");
    	Admin admin = adminService.currentAdmin();
    	Chain chain = chainService.findChain(admin.getId());
    	return (chain != null) ? mapToResponse(chain) : null;
	}
	
	@RequestMapping(value = "/makeChain", method = RequestMethod.POST)
    public ChainResponse makeChain() {
    	System.out.println("Processing '/makeChain' request");
    	
    	Admin admin = adminService.currentAdmin();
    	
    	List<Participant> listOfUsers = participantService.participants(admin);
    	System.out.println("Found " + listOfUsers.size() + " participants.");

    	List<Long> listOfUserIds = listOfUsers.stream().map(Participant::getId).collect(Collectors.toList());
    	List<GiverRecieverPair> pairs = pairSorter.splitIntoPairs(listOfUserIds);
    	System.out.println("Created " + pairs.size() + " pairs.");

    	Chain chain = chainService.findChain(admin.getId());
    	if (chain != null) {
        	System.out.println("Chain already exists for admin " + admin.getId());
        	System.out.println("Remaking the chain!");
        	chain = chainService.remakeChain(chain, pairs);
    	}
    	else {
    		chain = chainService.makeChain(admin.getId(), pairs);
    	}
    	return mapToResponse(chain);
	}
	
	private ChainResponse mapToResponse(Chain chain) {
		List<ChainResponse.PairResponse> pairs = new ArrayList<>();
		chain.getPairs().forEach(pair -> {
			ChainResponse.PairResponse pairResponse = new ChainResponse.PairResponse();
			pairResponse.setGiverId(pair.getGiverId());
			pairResponse.setReceiverId(pair.getReceiverId());
			pairResponse.setGiverName(participantName(pair.getGiverId()));
			pairResponse.setReceiverName(participantName(pair.getReceiverId()));
			pairs.add(pairResponse);
		});
		
		ChainResponse response = new ChainResponse();
		response.setPairs(pairs);
		return response;
	}
	
	private String participantName(long participantId) {
		Participant participant = participantService.participantById(participantId);
		return (participant != null) ? participant.getName() : null;
	}
}
