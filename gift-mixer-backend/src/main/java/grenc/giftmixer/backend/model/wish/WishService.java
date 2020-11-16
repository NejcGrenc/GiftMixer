package grenc.giftmixer.backend.model.wish;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WishService {
	
	@Autowired
	private WishRepository wishRepository;
	
    public Wish saveWish(long participantId, String wishContent) {
    	System.out.println("Processing '/saveWish' request from user: " + participantId);
    	
    	Wish wish;
    	Optional<Wish> optionalWish = wishRepository.findByParticipantId(participantId);
    	if (optionalWish.isPresent()) {
    		wish = optionalWish.get();
    	} else {
    		wish = new Wish();
    		wish.setParticipantId(participantId);
    	}
    	
    	wish.setWishContent(wishContent);
    	wish = wishRepository.save(wish);
    	return wish;
    }
    
    public Wish fetchWish(long participantId) {
    	Optional<Wish> optionalWish = wishRepository.findByParticipantId(participantId);
    	if (optionalWish.isPresent()) {
    		return optionalWish.get();
    	} else {
    		return null;
    	}
    }
    
}
