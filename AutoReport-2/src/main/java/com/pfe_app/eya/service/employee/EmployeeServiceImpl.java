package com.pfe_app.eya.service.employee;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pfe_app.eya.dto.SingleEmployeeDto;
import com.pfe_app.eya.entities.User;
import com.pfe_app.eya.repository.UserRepository;

import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final UserRepository userRepository;

	@Override
	public SingleEmployeeDto getEmployeeById(Long employeeId) {
		Optional<User> optionalUser = userRepository.findById(employeeId);
		
		SingleEmployeeDto singleEmployeeDto = new SingleEmployeeDto();
		optionalUser.ifPresent(user -> singleEmployeeDto.setEmployeeDto(user.getEmployeeDto()));		
		return singleEmployeeDto;
	}
	
}
