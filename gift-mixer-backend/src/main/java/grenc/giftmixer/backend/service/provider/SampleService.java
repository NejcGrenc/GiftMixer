package grenc.giftmixer.backend.service.provider;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.model.UserVerificationRequest;

@RestController
public class SampleService {

    @RequestMapping(value = "/sample", method = RequestMethod.POST)
	public String verifyUser(@RequestBody UserVerificationRequest request) {
    	System.out.println("Processing sample");
    	return "I am the best!";
	}
}
