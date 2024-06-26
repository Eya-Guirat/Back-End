package com.pfe_app.eya.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.pfe_app.eya.dto.ProjectDto;
import com.pfe_app.eya.dto.SingleEmployeeDto;
import com.pfe_app.eya.dto.SingleProjectDto;
import com.pfe_app.eya.dto.SingleTicketDto;
import com.pfe_app.eya.dto.SingleVacationDto;
import com.pfe_app.eya.dto.TicketDto;
import com.pfe_app.eya.dto.VacationDto;
import com.pfe_app.eya.entities.User;
import com.pfe_app.eya.entities.project;
import com.pfe_app.eya.service.employee.EmployeeService;

import jakarta.validation.ConstraintViolationException;
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
	public ResponseEntity<List<ProjectDto>> getAllProjects(){
	 	List<ProjectDto> allProjects = employeeService.getAllProjects();
	 	return ResponseEntity.ok(allProjects);
	}
	
	@DeleteMapping("/project/{projectId}")
	public ResponseEntity<Void> deleteProject(@PathVariable Long projectId){
		employeeService.deleteProject(projectId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/project/{projectId}")
	public ResponseEntity<SingleProjectDto> getProjectById(@PathVariable Long projectId){
		SingleProjectDto singleProjectDto = employeeService.getProjectById(projectId);
		if (singleProjectDto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(singleProjectDto);
	} 
	
	@PutMapping("/project/{projectId}")
	public ResponseEntity<?> updateProject(@PathVariable Long projectId,@RequestBody ProjectDto projectDto ){
			
		ProjectDto updatedProjectDto = employeeService.updateProject(projectId,projectDto);
		if (updatedProjectDto == null)
			return new ResponseEntity<>("something went wrong.", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.OK).body(updatedProjectDto);
	}

	
	@PostMapping("/vacation")
	public ResponseEntity<?> applyVacation(@RequestBody VacationDto vacationDto) {
		VacationDto submittedVacationDto = employeeService.applyVacation(vacationDto);
		if (submittedVacationDto == null)
			return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(submittedVacationDto);
	}
	
	@GetMapping("/vacation/{employeeId}")
	public ResponseEntity<List<VacationDto>> getAllAppliedVacationsByEmployeeId(@PathVariable Long employeeId){
	 	List<VacationDto> vacationDtos = employeeService.getAllAppliedVacationsByEmployeeId(employeeId);
	 	if (vacationDtos == null) return ResponseEntity.notFound().build();
	 	return ResponseEntity.ok(vacationDtos);
	}
	
	@DeleteMapping("/vacation/{vacationId}")
	public ResponseEntity<Void> deleteVacation(@PathVariable Long vacationId){
		employeeService.deleteVacation(vacationId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{employeeId}/vacation/{vacationId}")
	public ResponseEntity<?> updateVacation(@PathVariable Long vacationId,@RequestBody VacationDto vacationDto, @PathVariable Long employeeId ){
			
		VacationDto updatedVacationDto = employeeService.updateVacation(vacationId,vacationDto);
		if (updatedVacationDto == null)
			return new ResponseEntity<>("something went wrong.", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.OK).body(updatedVacationDto);
	}
	
	@GetMapping("/{employeeId}/vacation/{vacationId}")
	public ResponseEntity<SingleVacationDto> getVacationById(@PathVariable Long vacationId, @PathVariable Long employeeId) {
		SingleVacationDto singleVacationDto = employeeService.getVacationById(vacationId);
	    if (singleVacationDto == null)
	        return ResponseEntity.notFound().build();
	    return ResponseEntity.ok(singleVacationDto);
	}
	
	@PostMapping("/ticket")
	public ResponseEntity<?> applyTicket(@RequestBody TicketDto ticketDto) {
	    try {
	        TicketDto submittedTicketDto = employeeService.applyTicket(ticketDto);
	        if (submittedTicketDto == null)
	            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
	        return ResponseEntity.status(HttpStatus.CREATED).body(submittedTicketDto);
	    } catch (DataIntegrityViolationException e) {
	        if (e.getCause() instanceof ConstraintViolationException) {
	            return new ResponseEntity<>("A ticket with the same name already exists for today.", HttpStatus.BAD_REQUEST);
	        }
	        throw e;
	    }
	}
	
	@GetMapping("/ticket/{employeeId}")
	public ResponseEntity<List<TicketDto>> getAllTickets(@PathVariable Long employeeId){
	 	List<TicketDto> ticketDtos = employeeService.getAllTickets(employeeId);
	 	if (ticketDtos == null) return ResponseEntity.notFound().build();
	 	return ResponseEntity.ok(ticketDtos);
	}
	
	@DeleteMapping("/ticket/{ticketId}")
	public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId){
		employeeService.deleteTicket(ticketId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{employeeId}/ticket/{ticketId}")
	public ResponseEntity<SingleTicketDto> getTicketById(@PathVariable Long ticketId, @PathVariable Long employeeId) {
	    SingleTicketDto singleTicketDto = employeeService.getTicketById(ticketId);
	    if (singleTicketDto == null)
	        return ResponseEntity.notFound().build();
	    return ResponseEntity.ok(singleTicketDto);
	}
	
	@PutMapping("/{employeeId}/ticket/{ticketId}")
	public ResponseEntity<?> updateTicket(@PathVariable Long ticketId,@RequestBody TicketDto ticketDto, @PathVariable Long employeeId ){
			
		TicketDto updatedTicketDto = employeeService.updateTicket(ticketId,ticketDto);
		if (updatedTicketDto == null)
			return new ResponseEntity<>("something went wrong.", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.OK).body(updatedTicketDto);
	}

	
	@PutMapping("/{employeeId}")
	public ResponseEntity<?> updateEmployee(@PathVariable Long employeeId,@RequestBody EmployeeDto employeeDto ){
			
		EmployeeDto createdEmployeeDto = employeeService.updateEmployee(employeeId,employeeDto);
		if (createdEmployeeDto == null)
			return new ResponseEntity<>("something went wrong.", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployeeDto);
	}

}
