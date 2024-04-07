package com.pfe_app.eya.service.employee;

import com.pfe_app.eya.dto.ProjectDto;
import com.pfe_app.eya.dto.SingleEmployeeDto;

public interface EmployeeService {

	SingleEmployeeDto getEmployeeById(Long employeeId);

	ProjectDto applyProject(ProjectDto projectDto);

}
