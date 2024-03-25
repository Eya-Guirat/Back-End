package com.pfe_app.eya.service.admin;

import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pfe_app.eya.dto.EmployeeDto;
import com.pfe_app.eya.entities.User;
import com.pfe_app.eya.enums.UserRole;
import com.pfe_app.eya.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
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


	@Override
	public Optional<User> getUser(String email, String password) {
		
		return userRepository.findFirstByEmail(email);
	}


	@Override
	public EmployeeDto postEmployee(EmployeeDto employeeDto) {
		Optional<User> optionalUser = userRepository.findFirstByEmail(employeeDto.getEmail());
		if (optionalUser.isEmpty()) {
			User user = new User();
			BeanUtils.copyProperties(employeeDto, user);
			user.setPassword(new BCryptPasswordEncoder().encode(employeeDto.getPassword()));
			user.setRole(UserRole.EMPLOYEE);
			User createdUser = userRepository.save(user);
			EmployeeDto createdEmployeeDto = new EmployeeDto();
			createdEmployeeDto.setId(createdUser.getId());
			createdEmployeeDto.setEmail(createdUser.getEmail());
			return createdEmployeeDto;
		}
		return null;
	}
}
