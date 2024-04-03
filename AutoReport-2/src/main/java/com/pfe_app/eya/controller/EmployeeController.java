package com.pfe_app.eya.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe_app.eya.dto.EmployeeDto;
import com.pfe_app.eya.dto.SingleEmployeeDto;
import com.pfe_app.eya.service.employee.EmployeeService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<SingleEmployeeDto> getEmployeeById(@PathVariable Long employeeId){
		SingleEmployeeDto singleEmployeeDto = employeeService.getEmployeeById(employeeId);
		if (singleEmployeeDto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(singleEmployeeDto);
	}

}
