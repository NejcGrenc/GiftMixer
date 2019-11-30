package grenc.giftmixer.backend.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.model.Pair;
import grenc.giftmixer.backend.model.rest.RestResponse;
import grenc.giftmixer.backend.service.delegate.PairSorter;
import grenc.giftmixer.backend.service.delegate.WishFiles;

@RestController
public class WishersReceiversDistributor {

	private String credentialsEmail = "Povabljen/a si na sodelovanje v Božičnem žrebu. Izpolni svojo željo na tem naslovu (klikni na povezavo):\n\n";
	
	@Autowired
	WishFiles wishFiles;
	
	@Autowired
	WishService wishService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PairSorter pairSorter;
	
	@Autowired
	EmailService emailService;
	
	@Value("${host.address.darilo}")
	String hostAddressDarilo;
	
    @RequestMapping(value = "/distributeWishes", method = RequestMethod.POST)
	public Boolean distributeWishes() {
    	try {
			List<String> allUsers = wishFiles.findAllWishFiles();
			List<Pair> wishPairs = pairSorter.splitIntoPairs(allUsers);
			for (Pair pair : wishPairs) {
				RestResponse<String> wish = wishService.fetchWish(pair.wisher);
				String receiverEmail = userService.fetchEmailForUser(pair.receiver);
				if (receiverEmail == null) {
					continue;
				}
				
				emailService.sendSingleWish(receiverEmail, pair.wisher, wish.value);
			}
			return true;
			
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
	}
    
    @RequestMapping(value = "/distributeInvitations", method = RequestMethod.POST)
	public Boolean distributeInvitations() {
    	try {
			List<String> allUsers = wishFiles.findAllWishFiles();
			for (String userName : allUsers) {
				String receiverEmail = userService.fetchEmailForUser(userName);
				String credentials = userService.fetchCredentialsForUser(userName);
				if (receiverEmail == null || credentials == null) {
					continue;
				}
				
				String privateAddress = hostAddressDarilo + "/" + credentials;
				String emailContent = credentialsEmail + privateAddress;
				emailService.sendInvitation(receiverEmail, emailContent);
			}
			return true;
			
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
	}

}
