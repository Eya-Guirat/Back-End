package com.pfe_app.eya.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe_app.eya.dto.EmployeeDto;
import com.pfe_app.eya.service.admin.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	
	
	private final AdminService adminService;
	
	
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	
	@PostMapping("/employee")
	public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto employeeDto ){
		EmployeeDto createdEmployeeDto = adminService.postEmployee(employeeDto);
		if (createdEmployeeDto == null)
			return new ResponseEntity<>("something went wrong.", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployeeDto);
	}


}
