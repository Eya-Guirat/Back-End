package com.pfe_app.eya.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe_app.eya.dto.EmployeeDto;
import com.pfe_app.eya.dto.SingleEmployeeDto;
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

	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDto>> getallEmployee(){
	 	List<EmployeeDto> allEmlpoyees = adminService.getAllEmployee();
	 	return ResponseEntity.ok(allEmlpoyees);
	}
	
	@DeleteMapping("/employee/{employeeId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId){
		adminService.deleteEmployee(employeeId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<SingleEmployeeDto> getEmployeeById(@PathVariable Long employeeId){
		SingleEmployeeDto singleEmployeeDto = adminService.getEmployeeById(employeeId);
		if (singleEmployeeDto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(singleEmployeeDto);
	}
	
	@PutMapping("/employee/{employeeId}")
	public ResponseEntity<?> updateEmployee(@PathVariable Long employeeId,@RequestBody EmployeeDto employeeDto ){
			
		EmployeeDto createdEmployeeDto = adminService.updateEmployee(employeeId,employeeDto);
		if (createdEmployeeDto == null)
			return new ResponseEntity<>("something went wrong.", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployeeDto);
	}
	

}
