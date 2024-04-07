package com.pfe_app.eya.service.employee;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe_app.eya.dto.ProjectDto;
import com.pfe_app.eya.dto.SingleEmployeeDto;
import com.pfe_app.eya.entities.User;
import com.pfe_app.eya.entities.project;
import com.pfe_app.eya.repository.ProjectRepository;
import com.pfe_app.eya.repository.UserRepository;

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

	@Override
	public ProjectDto applyProject(ProjectDto projectDto) {
		Optional<User> optionalUser = userRepository.findById(projectDto.getUserid());
		if (optionalUser.isPresent()) {
			project projectt = new project();
			projectt.setName(projectDto.getName());
			projectt.setUser(optionalUser.get());
			project SubmittedProject = projectRepository.save(projectt);
			ProjectDto projectDto1 = new ProjectDto();
			projectDto1.setId(SubmittedProject.getId());
			return projectDto1;
		}
		return null;
	}
	
}
