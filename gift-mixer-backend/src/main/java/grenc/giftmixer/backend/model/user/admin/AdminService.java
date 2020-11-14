package grenc.giftmixer.backend.model.user.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin currentAdmin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication.getName() == null) {
			throw new RuntimeException("Could not determine admin username from authentication. Bad authenticaion.");
		}
		String adminUsername = authentication.getName();
		
    	System.out.println("Fetching admin for username " + adminUsername);
		Admin admin = findAdmin(adminUsername);
		if (admin == null) {
			System.out.println("Admin not found for username " + adminUsername + ". Creating new admin.");
			admin = createNewAdmin(adminUsername);
		}
		
		return admin;
	}
	
	@Deprecated //Retrieving admins by username is dangerous due to possible duplication!
	public Admin findAdmin(String username) {
		List<Admin> admins = adminRepository.findByUsername(username);
		if (admins == null || admins.isEmpty()) {
			System.out.println("No admin found for username " + username);
			return null;
		}
		if (admins.size() > 1) {
			System.out.println("WARN: Multiple admins found for username " + username + ". Retrieving the first one.");
		}
		return admins.get(0);
	}
	
	public Admin createNewAdmin(String username) {
		Admin newAdmin = new Admin();
		newAdmin.setUsername(username);
		newAdmin = adminRepository.save(newAdmin);
		return newAdmin;
	}

}
