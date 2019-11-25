package grenc.giftmixer.backend.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserVerification {

	@Value("${storage.users.file}")
	String usersFile;
	
    @RequestMapping(value = "/verifyUser", method = RequestMethod.POST)
	public Boolean verifyUser(@RequestBody UserVerificationRequest request) {
    	System.out.println("Processing verification of user: " + request.userName);

    	boolean success = false;
		Map<String, String> verificationMap = verificationMap();
		if (verificationMap.containsKey(request.userName)) {
			success = verificationMap.get(request.userName).equals(request.userCode);
		}
		
    	System.out.println("User: " + request.userName + " " + (success ? "successfully" : "unsuccessfully") + " verificated");
    	return success;
	}
	
	public Map<String, String> verificationMap() {
		Map<String, String> map = new HashMap<String, String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(usersFile))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	        	String user = line.split(":")[0];
	        	String code = line.split(":")[1];

	        	map.put(user, code);
	        }
			return map;
			
		} catch (IndexOutOfBoundsException | IOException e) {
			e.printStackTrace();
			return new HashMap<String, String>();
		}
	}
	
}

class UserVerificationRequest {
	public String userName;
	public String userCode;
}
