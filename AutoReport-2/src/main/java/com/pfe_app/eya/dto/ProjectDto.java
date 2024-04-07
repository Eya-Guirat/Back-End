package com.pfe_app.eya.dto;

import lombok.Data;

@Data
public class ProjectDto {
	
	private Long id;
	private String name;
	private Long userid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
}
