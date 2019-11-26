 package grenc.giftmixer.backend.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.model.WishListResponse;
import grenc.giftmixer.backend.model.WishRequest;
import grenc.giftmixer.backend.model.WishResponse;
import grenc.giftmixer.backend.service.delegate.WishFiles;


@RestController
public class WishService {
	
	@Autowired
	WishFiles wishFiles;
	
	@Autowired
	UserService userService;
	
    @RequestMapping(value = "/saveWish", method = RequestMethod.POST)
    public boolean saveWish(@RequestBody WishRequest request) {
    	System.out.println("Processing '/saveWish' request from user: " + request.userName);
    	
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(wishFiles.filePathForName(request.userName)));
            writer.write(request.wishContent);
			writer.close();
	        return true;
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    @RequestMapping(value = "/fetchWish", method = RequestMethod.POST)
    public WishResponse fetchWish(@RequestBody String userName) {
    	System.out.println("Processing '/fetchWish' request from user: " + userName);
    	
    	StringBuilder content = new StringBuilder();
    	try (BufferedReader br = new BufferedReader(new FileReader(wishFiles.filePathForName(userName)))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	        	content.append(line);
	        	content.append("\n");
	        }
	        
	        WishResponse response = new WishResponse();
	        response.success = true;
	        response.wishContent = content.toString();
			return response;
			
		} catch (IndexOutOfBoundsException | IOException e) {
			e.printStackTrace();
			return new WishResponse();
		}
    }
    
    @RequestMapping(value = "/wishList", method = RequestMethod.POST)
    public WishListResponse wishList() {
    	System.out.println("Processing '/wishList' request");
    	 
    	try {
	    	List<String> existingWishes = wishFiles.findAllWishFiles();
	    	if (existingWishes == null) {
	        	System.out.println("An error occourred while trying to find existing wishes.");
	        	return new WishListResponse();
	    	}
	    	
	    	List<String> allUsers = userService.users();
	    	if (allUsers == null) {
	        	System.out.println("An error occourred while trying to find all users.");
	        	return new WishListResponse();
	    	}
	    	
	    	WishListResponse response = new WishListResponse();
	    	response.success = true;
	    	response.allUsers = allUsers;
	    	response.madeWishes = existingWishes;
	    	return response;
	    	
		} catch (Exception e) {
			e.printStackTrace();
			return new WishListResponse();
		}
    }
    
}
