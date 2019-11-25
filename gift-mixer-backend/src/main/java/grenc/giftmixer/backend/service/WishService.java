package grenc.giftmixer.backend.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.model.WishRequest;
import grenc.giftmixer.backend.model.WishResponse;


@RestController
public class WishService {
	
	@Value("${storage.wish.folder}")
	private String wishPath;
	
    @RequestMapping(value = "/saveWish", method = RequestMethod.POST)
    public boolean saveWish(@RequestBody WishRequest request) {
    	System.out.println("Processing '/saveWish' request from user: " + request.userName);
    	
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(wishPath + File.separator + request.userName + ".txt"));
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
    	try (BufferedReader br = new BufferedReader(new FileReader(wishPath + File.separator + userName + ".txt"))) {
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
    
}
