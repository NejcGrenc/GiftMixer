package grenc.giftmixer.backend.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.model.WishRequest;


@RestController
public class SaveWish {
	
	@Value("${storage.wish.folder}")
	private String wishPath;
	
    @RequestMapping(value = "/saveWish", method = RequestMethod.POST)
    public String saveWish(@RequestBody WishRequest request) {
    	System.out.println(request);
    	
        try {
        	String fileName = wishPath + "/" + request.userName + ".txt";
        	System.out.println(fileName);

            BufferedWriter writer = new BufferedWriter(new FileWriter(wishPath + File.separator + request.userName + ".txt"));
            writer.write(request.wishContent);
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return "FAIL";
		}
        return "OK";
    }
    
    
    
}
