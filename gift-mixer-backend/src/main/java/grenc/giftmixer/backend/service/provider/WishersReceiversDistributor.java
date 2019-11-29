package grenc.giftmixer.backend.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.model.Pair;
import grenc.giftmixer.backend.model.rest.RestResponse;
import grenc.giftmixer.backend.service.delegate.PairSorter;
import grenc.giftmixer.backend.service.delegate.WishFiles;

@RestController
public class WishersReceiversDistributor {
	
	@Autowired
	WishFiles wishFiles;
	
	@Autowired
	WishService wishService;
	
	@Autowired
	PairSorter pairSorter;
	
	@Autowired
	EmailService emailService;
	
    @RequestMapping(value = "/distributeWishes", method = RequestMethod.GET)
	public Boolean distributeWishes() {
    	try {
			List<String> allUsers = wishFiles.findAllWishFiles();
			List<Pair> wishPairs = pairSorter.splitIntoPairs(allUsers);
			for (Pair pair : wishPairs) {
				RestResponse<String> wish = wishService.fetchWish(pair.wisher);
				emailService.sendSingleWish(pair.receiver, pair.wisher, wish.value);
			}
			return true;
			
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
	}
    
    

}
