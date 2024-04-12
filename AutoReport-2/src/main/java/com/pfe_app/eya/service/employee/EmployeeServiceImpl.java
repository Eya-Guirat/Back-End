package com.pfe_app.eya.service.employee;

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
import com.pfe_app.eya.entities.User;
import com.pfe_app.eya.entities.project;
import com.pfe_app.eya.repository.ProjectRepository;
import com.pfe_app.eya.repository.UserRepository;

import jakarta.mail.Session;
import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProjectRepository projectRepository;

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
	public Optional<project> getAllProjects(User user) {
		return projectRepository.findAllByUser(user);
	}


	


	
}
