package com.pfe_app.eya.service.admin;

import com.pfe_app.eya.dto.EmployeeDto;
import com.pfe_app.eya.dto.SingleEmployeeDto;
import com.pfe_app.eya.dto.VacationDto;

import java.util.List;
import java.util.Optional;
import com.pfe_app.eya.entities.User;

public interface AdminService {

	Optional<User> getUser(String email, String password);

	EmployeeDto postEmployee(EmployeeDto employeeDto);

	List<EmployeeDto> getAllEmployee();
	
	void deleteEmployee(Long employeeId);
	
	SingleEmployeeDto getEmployeeById(Long employeeId);

	EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto);

	List<VacationDto> getAllAppliedVacations();
}
