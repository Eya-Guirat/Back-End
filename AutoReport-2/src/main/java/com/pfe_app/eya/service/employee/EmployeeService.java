package com.pfe_app.eya.service.employee;

import java.util.List;
import java.util.Optional;

import com.pfe_app.eya.dto.ProjectDto;
import com.pfe_app.eya.dto.SingleEmployeeDto;
import com.pfe_app.eya.dto.SingleProjectDto;
import com.pfe_app.eya.dto.SingleTicketDto;
import com.pfe_app.eya.dto.SingleVacationDto;
import com.pfe_app.eya.dto.TicketDto;
import com.pfe_app.eya.dto.VacationDto;
import com.pfe_app.eya.entities.User;
import com.pfe_app.eya.entities.project;

public interface EmployeeService {

	SingleEmployeeDto getEmployeeById(Long employeeId);

	ProjectDto applyProject(ProjectDto projectDto);

	List<ProjectDto> getAllProjects();

	void deleteProject(Long projectId);

	SingleProjectDto getProjectById(Long projectId);

	ProjectDto updateProject(Long projectId, ProjectDto projectDto);

	VacationDto applyVacation(VacationDto vacationDto);

	List<VacationDto> getAllAppliedVacationsByEmployeeId(Long employeeId);

	TicketDto applyTicket(TicketDto ticketDto);

	List<TicketDto> getAllTickets(Long employeeId);

	void deleteTicket(Long ticketId);

	SingleTicketDto getTicketById(Long ticketId);

	TicketDto updateTicket(Long ticketId, TicketDto ticketDto);

	void deleteVacation(Long vacationId);

	SingleVacationDto getVacationById(Long vacationId);

	VacationDto updateVacation(Long vacationId, VacationDto vacationDto);
	


}
