package com.pfe_app.eya.service.employee;

import java.util.List;
import java.util.Optional;

import com.pfe_app.eya.dto.ProjectDto;
import com.pfe_app.eya.dto.SingleEmployeeDto;
import com.pfe_app.eya.entities.User;
import com.pfe_app.eya.entities.project;

public interface EmployeeService {

	SingleEmployeeDto getEmployeeById(Long employeeId);

	ProjectDto applyProject(ProjectDto projectDto);

	Optional<project> getAllProjects(User user);


}
