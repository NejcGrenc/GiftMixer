package grenc.giftmixer.backend.controller.adminconsole;

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
	
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
    public Admin admin(@RequestBody String adminUsername) {
    	System.out.println("Processing '/admin' request");
    	return adminService.currentAdmin();
	}
}
