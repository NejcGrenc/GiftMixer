package grenc.giftmixer.backend.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.controller.wish.WishController;
import grenc.giftmixer.backend.model.chain.GiverRecieverPair;
import grenc.giftmixer.backend.service.EmailService;
import grenc.giftmixer.backend.service.delegate.PairSorter;
import grenc.giftmixer.backend.service.delegate.WishFiles;

@RestController
public class WishersReceiversDistributor {

	private String credentialsEmail = "Povabljen/a si na sodelovanje v Božičnem žrebu. Izpolni svojo željo na tem naslovu (klikni na povezavo):"
			+ "<br /><br />";
	private String credentialsEmailEnd = "<br /><br />"
			+ "Skupna cena želja naj bo v okolici 50€.<br />"
			+ "Prosim odpri to povezavo v Firefox ali Chromu na računalniku (ostalo morda ne bo delovalo pravilno)!<br />"
			+ "Ko bodo vsi izpolnili svoje želje, bodo le-te naključno razdeljene med skrivne božičke.";
	
	@Autowired
	WishFiles wishFiles;
	
	@Autowired
	WishController wishService;
	
	@Autowired
	PairSorter pairSorter;
	
	@Autowired
	EmailService emailService;
	
	@Value("${host.address.darilo}")
	String hostAddressDarilo;
	
    @RequestMapping(value = "/distributeWishes", method = RequestMethod.POST)
	public Boolean distributeWishes() {
    	System.out.println("/distributeWishes");
    	try {
			List<String> allUsers = wishFiles.findAllWishFiles();
//			List<GiverRecieverPair> wishPairs = pairSorter.splitIntoPairs(allUsers);
//			for (GiverRecieverPair pair : wishPairs) {
//				RestResponse<String> wish = wishService.fetchWish(pair.wisher, "<br />");
//				String receiverEmail = userService.fetchEmailForUser(pair.receiver);
//				if (receiverEmail == null) {
//					System.out.println("Email will not be sent. Receiver is null.");
//					continue;
//				}
//				
//				emailService.sendSingleWish(receiverEmail, pair.getReceiverId(), wish.value);
//			}
			return true;
			
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
	}
    
//    @RequestMapping(value = "/distributeInvitations", method = RequestMethod.POST)
//	public Boolean distributeInvitations() {
//    	System.out.println("/distributeInvitations");
//    	try {
//			RestResponse<List<String>> response = userService.users();
//			if (! response.success) {
//				System.out.println("No users found");
//				return false;
//			}
//			
//			List<String> allUsers = response.value;
//			for (String userName : allUsers) {
//				String receiverEmail = userService.fetchEmailForUser(userName);
//				String credentials = userService.fetchCredentialsForUser(userName);
//				if (receiverEmail == null || credentials == null) {
//					System.out.println("Email will not be sent. Receiver [" + receiverEmail + "] or credentials [" + credentials + "] are null.");
//					continue;
//				}
//				
//				String privateAddress = hostAddressDarilo + "/" + credentials;
//				String emailContent = credentialsEmail + "<a href=\"" + privateAddress + "\">" + privateAddress + "</a>" + credentialsEmailEnd;
//				emailService.sendInvitation(receiverEmail, emailContent);
//			}
//			return true;
//			
//    	} catch (Exception e) {
//    		e.printStackTrace();
//    		return false;
//    	}
//	}

}
