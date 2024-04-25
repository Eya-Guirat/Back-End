package com.pfe_app.eya.service.employee;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pfe_app.eya.dto.ProjectDto;
import com.pfe_app.eya.dto.SingleEmployeeDto;
import com.pfe_app.eya.dto.SingleProjectDto;
import com.pfe_app.eya.dto.SingleTicketDto;
import com.pfe_app.eya.dto.TicketDto;
import com.pfe_app.eya.dto.VacationDto;
import com.pfe_app.eya.entities.Ticket;
import com.pfe_app.eya.entities.User;
import com.pfe_app.eya.entities.Vacation;
import com.pfe_app.eya.entities.project;
import com.pfe_app.eya.enums.TicketStatus;
import com.pfe_app.eya.enums.VacationStatus;
import com.pfe_app.eya.repository.ProjectRepository;
import com.pfe_app.eya.repository.TicketRepository;
import com.pfe_app.eya.repository.UserRepository;
import com.pfe_app.eya.repository.VacationRepository;

import jakarta.mail.Session;
import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private VacationRepository vacationRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	

	@Override
	public SingleEmployeeDto getEmployeeById(Long employeeId) {
		Optional<User> optionalUser = userRepository.findById(employeeId);
		
		SingleEmployeeDto singleEmployeeDto = new SingleEmployeeDto();
		optionalUser.ifPresent(user -> singleEmployeeDto.setEmployeeDto(user.getEmployeeDto()));		
		return singleEmployeeDto;
	}

	public String authUser() {
	Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
	if (!(authentication instanceof AnonymousAuthenticationToken)) {
	    String currentUserName = authentication.getName();
	    return currentUserName;
	}
	return null;
	}
	@Override
	public ProjectDto applyProject(ProjectDto projectDto) {
		//Optional<User> optionalUser = userRepository.findById("get the user from the session");
		Optional<User> optionalUser = userRepository.findFirstByEmail(authUser());
		
		if (optionalUser.isPresent()) {
			project projectt = new project();
			projectt.setName(projectDto.getName());
			projectt.setId(100);
			projectt.setUser(optionalUser.get());
			project SubmittedProject = projectRepository.save(projectt);
			ProjectDto projectDto1 = new ProjectDto();
			projectDto1.setId(SubmittedProject.getId());
			return projectDto1;
		}
		return null;
	}

	@Override
	public List<ProjectDto> getAllProjects() {
		return projectRepository.findAll().stream().map(project::getProjectDto).collect(Collectors.toList());
	}

	@Override
	public void deleteProject(Long projectId) {
		projectRepository.deleteById(projectId);
		
	}

	@Override
	public SingleProjectDto getProjectById(Long projectId) {
		Optional<project> optionalProject = projectRepository.findById(projectId);
		if (optionalProject.isPresent()) {
			SingleProjectDto singleProjectDto = new SingleProjectDto();
			singleProjectDto.setProjectDto(optionalProject.get().getProjectDto());
			return singleProjectDto;
		}
		return null;
	}

	@Override
	public ProjectDto updateProject(Long projectId, ProjectDto projectDto) {
		Optional<project> optionalProject = projectRepository.findById(projectId);
		if (optionalProject.isPresent()) {
			project updateProject = optionalProject.get();
			updateProject.setName(projectDto.getName());
			project updatedProject = projectRepository.save(updateProject);
			ProjectDto updatedProjectDto = new ProjectDto();
			updatedProjectDto.setId(updatedProject.getId());
			return updatedProjectDto;
		}
		return null;
	}

	@Override
	public VacationDto applyVacation(VacationDto vacationDto) {
		Optional<User> optionalUser = userRepository.findFirstByEmail(authUser());
		if (optionalUser.isPresent()) {
			Vacation vacation = new Vacation();
			vacation.setType(vacationDto.getType());
			vacation.setSd(vacationDto.getSd());
			vacation.setEd(vacationDto.getEd());
			vacation.setDate(new Date());
			vacation.setVacationStatus(VacationStatus.Pending);
			vacation.setUser(optionalUser.get());
			Vacation SubmittedVacation = vacationRepository.save(vacation);
			VacationDto vacationDto1 = new VacationDto();
			vacationDto1.setId(SubmittedVacation.getId());
			return vacationDto1;
		}
		return null;
	}

	@Override
	public List<VacationDto> getAllAppliedVacationsByEmployeeId(Long employeeId) {
		return vacationRepository.findAllByUserId(employeeId).stream().map(Vacation::getVacationDto).collect(Collectors.toList());
	}

	@Override
	public TicketDto applyTicket(TicketDto ticketDto) {
		Optional<User> optionalUser = userRepository.findFirstByEmail(authUser());
		
		if (optionalUser.isPresent()) {
			Ticket ticket = new Ticket();
			ticket.setTname(ticketDto.getTname());
			ticket.setDuration(ticketDto.getDuration());
			ticket.setDate(ticketDto.getDate());
			ticket.setDescription(ticketDto.getDescription());
			ticket.setTicketStatus(TicketStatus.ToDo);
			ticket.setUser(optionalUser.get());
			
			project project = projectRepository.findById(ticketDto.getProjectId()).orElseThrow();
			ticket.setProject(project);
			
			Ticket SubmittedTicket = ticketRepository.save(ticket);
			TicketDto ticketDto1 = new TicketDto();
			ticketDto1.setId(SubmittedTicket.getId());
			return ticketDto1;
		}
		return null;
	}

	@Override
	public List<TicketDto> getAllTickets(Long employeeId) {
		return ticketRepository.findAllByUserId(employeeId).stream().map(Ticket::getTicketDto).collect(Collectors.toList());
	}

	@Override
	public void deleteTicket(Long ticketId) {
		ticketRepository.deleteById(ticketId);
		
	}

	@Override
	public SingleTicketDto getTicketById(Long ticketId) {
	    Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
	    if (optionalTicket.isPresent()) {
	        SingleTicketDto singleTicketDto = new SingleTicketDto();
	        singleTicketDto.setTicketDto(optionalTicket.get().getTicketDto());
	        return singleTicketDto;
	    }
	    return null;
	}

	@Override
	public TicketDto updateTicket(Long ticketId, TicketDto ticketDto) {
		Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
		Optional<project> optionalProject = projectRepository.findById(ticketDto.getProjectId());
		if (optionalTicket.isPresent()) {
			Ticket updateTicket = optionalTicket.get();
			updateTicket.setTname(ticketDto.getTname());
			updateTicket.setDate(ticketDto.getDate());
			updateTicket.setDescription(ticketDto.getDescription());
			updateTicket.setDuration(ticketDto.getDuration());
			updateTicket.setTicketStatus(ticketDto.getTicketStatus());
			updateTicket.setProject(optionalProject.get());
			Ticket updatedTicket = ticketRepository.save(updateTicket);
			TicketDto updatedTicketDto = new TicketDto();
			updatedTicketDto.setId(updatedTicket.getId());
			return updatedTicketDto;
		}
		return null;
	}

	


	


	
}
