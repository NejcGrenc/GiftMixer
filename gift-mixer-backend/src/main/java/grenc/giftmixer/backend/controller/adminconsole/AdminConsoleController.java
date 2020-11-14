package grenc.giftmixer.backend.controller.adminconsole;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grenc.giftmixer.backend.controller.adminconsole.model.AdminConsoleRequest;
import grenc.giftmixer.backend.controller.adminconsole.model.AdminConsoleResponse;

@RestController
public class AdminConsoleController {

	@RequestMapping(value = "/adminData", method = RequestMethod.POST)
    public AdminConsoleResponse adminData(@RequestBody AdminConsoleRequest request) {
    	System.out.println("Processing '/adminData' request");
    	return new AdminConsoleResponse();
	}
}
