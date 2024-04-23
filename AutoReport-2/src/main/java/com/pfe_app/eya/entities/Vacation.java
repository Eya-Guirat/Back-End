package com.pfe_app.eya.entities;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfe_app.eya.dto.VacationDto;
import com.pfe_app.eya.enums.VacationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Vacation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String type;
	
	private Date sd;
	
	private Date ed;
	
	private Date date;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getSd() {
		return sd;
	}

	public void setSd(Date sd) {
		this.sd = sd;
	}

	public Date getEd() {
		return ed;
	}

	public void setEd(Date ed) {
		this.ed = ed;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public VacationStatus getVacationStatus() {
		return vacationStatus;
	}

	public void setVacationStatus(VacationStatus vacationStatus) {
		this.vacationStatus = vacationStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private VacationStatus vacationStatus;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;
	
	public VacationDto getVacationDto() {
		VacationDto vacationDto = new VacationDto();
		vacationDto.setId(id);
		vacationDto.setType(type);
		vacationDto.setSd(sd);
		vacationDto.setEd(ed);
		vacationDto.setDate(date);
		vacationDto.setVacationStatus(vacationStatus);
		vacationDto.setUserid(user.getId());
		vacationDto.setName(user.getName()); 
		return vacationDto;
	}
	
	
}
