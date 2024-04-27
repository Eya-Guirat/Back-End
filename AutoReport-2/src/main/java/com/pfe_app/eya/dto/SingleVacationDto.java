package com.pfe_app.eya.dto;

import lombok.Data;

@Data
public class SingleVacationDto {

	private VacationDto vacationDto;

	public VacationDto getVacationDto() {
		return vacationDto;
	}

	public void setVacationDto(VacationDto vacationDto) {
		this.vacationDto = vacationDto;
	} 
	
}
