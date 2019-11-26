package grenc.giftmixer.backend.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.model.User;
import grenc.giftmixer.backend.model.UserVerificationRequest;

@RestController
public class UserService {

	@Value("${storage.users.file}")
	String usersFile;
	
    @RequestMapping(value = "/verifyUser", method = RequestMethod.POST)
	public Boolean verifyUser(@RequestBody UserVerificationRequest request) {
    	System.out.println("Processing verification of user: " + request.userName);

    	boolean success = false;
		Map<String, User> verificationMap = userDataMap();
		if (verificationMap.containsKey(request.userName)) {
			success = verificationMap.get(request.userName).securityCode.equals(request.userCode);
		}
		
    	System.out.println("User: " + request.userName + " " + (success ? "successfully" : "unsuccessfully") + " verificated");
    	return success;
	}
	
	public List<String> users() {
    	System.out.println("Fetching list of all users");

		Map<String, User> verificationMap = userDataMap();
    	return new ArrayList<String>(verificationMap.keySet());
	}
	
	
	public Map<String, User> userDataMap() {
		Map<String, User> map = new HashMap<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(usersFile))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	        	String user  = line.split(":")[0];
	        	String type  = line.split(":")[1];
	        	String value = line.split(":")[2];

	        	addUserData(map, user, type, value);
	        }
			
		} catch (IndexOutOfBoundsException | IOException e) {
			throw new RuntimeException("Failure retrieving user data", e);
		}
		
		map.values().stream().forEach(userData -> verify(userData));
		
		return map;
	}
	
	private void addUserData(Map<String, User> map, String user, String type, String value) {
		if (! map.containsKey(user)) {
			map.put(user, new User(user));
		}
		User userData = map.get(user);
		
		switch(type) {
			case "code":
				userData.securityCode = value;
				break;
			case "email":
				userData.email = value;
				break;
			default:
				throw new RuntimeException(String.format("Unrecognizable user data [user: %s, type: %s, value: %s]", user, type, value));
		}
	}
	
	private void verify(User userData) {
		if (userData == null || userData.name == null || userData.email == null || userData.securityCode == null) {
			throw new RuntimeException("Invalid user data: " + userData.toString());
		}
	}
	
}
