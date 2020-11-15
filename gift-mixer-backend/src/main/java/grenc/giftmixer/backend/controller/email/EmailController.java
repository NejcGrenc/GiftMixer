package grenc.giftmixer.backend.controller.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.model.user.admin.Admin;
import grenc.giftmixer.backend.model.user.admin.AdminService;
import grenc.giftmixer.backend.service.provider.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value = "/email", method = RequestMethod.POST)
    public void email() {
    	System.out.println("Processing '/email' request");
    	emailService.sample();
	}
}
