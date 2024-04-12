package com.pfe_app.eya.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe_app.eya.dto.EmployeeDto;
import com.pfe_app.eya.dto.ProjectDto;
import com.pfe_app.eya.dto.SingleEmployeeDto;
import com.pfe_app.eya.entities.User;
import com.pfe_app.eya.entities.project;
import com.pfe_app.eya.service.employee.EmployeeService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<SingleEmployeeDto> getEmployeeById(@PathVariable Long employeeId){
		SingleEmployeeDto singleEmployeeDto = employeeService.getEmployeeById(employeeId);
		if (singleEmployeeDto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(singleEmployeeDto);
	}
	
	@PostMapping("/project")
	public ResponseEntity<?> applyProject(@RequestBody ProjectDto projectDto){
		System.out.println(projectDto);
		ProjectDto submittedProjectDto  = employeeService.applyProject(projectDto);
		if (submittedProjectDto == null)
			return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(submittedProjectDto);
			
	}
	
	@GetMapping("/projects")
	public ResponseEntity<Optional<project>> getAllProjects(@RequestBody User user){
	 	Optional<project> allProjects = employeeService.getAllProjects(user);
	 	return ResponseEntity.ok(allProjects);
	}
	

}
