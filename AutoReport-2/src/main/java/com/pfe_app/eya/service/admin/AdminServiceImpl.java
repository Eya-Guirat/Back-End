package com.pfe_app.eya.service.admin;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pfe_app.eya.dto.EmployeeDto;
import com.pfe_app.eya.dto.SingleEmployeeDto;
import com.pfe_app.eya.dto.VacationDto;
import com.pfe_app.eya.entities.User;
import com.pfe_app.eya.entities.Vacation;
import com.pfe_app.eya.enums.UserRole;
import com.pfe_app.eya.enums.VacationStatus;
import com.pfe_app.eya.repository.UserRepository;
import com.pfe_app.eya.repository.VacationRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private VacationRepository vacationRepository;
	
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


	@Override
	public List<EmployeeDto> getAllEmployee() {
		return userRepository.findAllByRole(UserRole.EMPLOYEE).stream().map(User::getEmployeeDto).collect(Collectors.toList());
	}


	@Override
	public void deleteEmployee(Long employeeId) {
		userRepository.deleteById(employeeId);
		
	}
	
	@Override
	public SingleEmployeeDto getEmployeeById(Long employeeId) {
		Optional<User> optionalUser = userRepository.findById(employeeId);
		
			SingleEmployeeDto singleEmployeeDto = new SingleEmployeeDto();
			optionalUser.ifPresent(user -> singleEmployeeDto.setEmployeeDto(user.getEmployeeDto()));		
			return singleEmployeeDto;
	}


	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
		Optional<User> optionalUser = userRepository.findById(employeeId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setName(employeeDto.getName());
			user.setGender(employeeDto.getGender());
			user.setDob(employeeDto.getDob());
			user.setEmail(employeeDto.getEmail());
			if (employeeDto.getPassword() != null && !employeeDto.getPassword().isEmpty()) {
				  user.setPassword(employeeDto.getPassword());
				}
			User updatedEmployee = userRepository.save(user);
			EmployeeDto updatedEmployeeDto = new EmployeeDto();
			updatedEmployeeDto.setId(updatedEmployee.getId());
			return updatedEmployeeDto;
		}
		return null;
	}

 
	@Override
	public List<VacationDto> getAllAppliedVacations() {
		return vacationRepository.findAll().stream().map(Vacation::getVacationDto).collect(Collectors.toList());
	}


	@Override
	public VacationDto changeVacationstatus(Long vacationId, String status) {
		Optional<Vacation> optionalVacation = vacationRepository.findById(vacationId);
		if(optionalVacation.isPresent()) {
			Vacation vacation = optionalVacation.get();
			if (Objects.equals(status, "Approve")){
				vacation.setVacationStatus(VacationStatus.Approved);
			} else {
				vacation.setVacationStatus(VacationStatus.Disapproved);
			}
			Vacation updatedVacation = vacationRepository.save(vacation);
			VacationDto updatedVacationDto = new VacationDto();
			updatedVacationDto.setId(updatedVacation.getId());
			return updatedVacationDto;
		}
		return null;
	}
}
