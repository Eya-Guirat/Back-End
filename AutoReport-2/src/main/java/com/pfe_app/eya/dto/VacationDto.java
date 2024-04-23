package com.pfe_app.eya.dto;

import java.util.Date;

import com.pfe_app.eya.enums.VacationStatus;

import lombok.Data;

@Data
public class VacationDto {
	
private Long id;
	
	private String type;
	
	private Date sd;
	
	private Date ed;
	
	private Date date;
	
	private VacationStatus vacationStatus;
	
	private Long userid;
	
	private String name;

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

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	 public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

}
