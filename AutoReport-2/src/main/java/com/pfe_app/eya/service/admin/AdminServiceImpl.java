package com.pfe_app.eya.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pfe_app.eya.entities.User;
import com.pfe_app.eya.enums.UserRole;
import com.pfe_app.eya.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AdminServiceImpl {

	
	private final UserRepository userRepository;
	
	public AdminServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@PostConstruct
	 public void createAdminAccount() {
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		if (adminAccount == null) {
			User admin = new User();
			admin.setEmail("admin@cgf.com");
			admin.setName("admin");
			admin.setRole(UserRole.ADMIN);
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(admin);
		}
			
		
	}
}
