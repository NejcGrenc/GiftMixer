package grenc.giftmixer.backend.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.model.user.admin.Admin;
import grenc.giftmixer.backend.model.user.admin.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/createVerifyAdmin", method = RequestMethod.POST)
    public Admin createVerifyAdmin(@RequestBody String adminUsername) {
    	System.out.println("Processing '/createVerifyAdmin' request");
    	Admin admin = adminService.currentAdmin();
    	if (admin == null) {
    		admin = adminService.createNewAdmin();
    	}
    	return admin;
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
    public Admin admin(@RequestBody String adminUsername) {
    	System.out.println("Processing '/admin' request");
    	return adminService.currentAdmin();
	}
}
