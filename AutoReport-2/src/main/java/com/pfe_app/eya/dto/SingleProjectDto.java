package com.pfe_app.eya.dto;

import lombok.Data;

@Data
public class SingleProjectDto {
	
	private ProjectDto projectDto ;

	public ProjectDto getProjectDto() {
		return projectDto;
	}

	public void setProjectDto(ProjectDto projectDto) {
		this.projectDto = projectDto;
	}



}
