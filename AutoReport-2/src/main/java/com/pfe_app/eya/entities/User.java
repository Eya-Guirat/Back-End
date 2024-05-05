package com.pfe_app.eya.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

import com.pfe_app.eya.dto.EmployeeDto;
import com.pfe_app.eya.enums.UserRole;

import jakarta.persistence.*;

@Entity
@Table(name = "users")

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private Date dob; 	
	
	private String gender;
	
	private UserRole role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	public EmployeeDto getEmployeeDto() {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(id);
		employeeDto.setName(name);
		employeeDto.setEmail(email);
		employeeDto.setPassword(password);
		employeeDto.setGender(gender);
		employeeDto.setDob(dob);
		return employeeDto;
	}
	
	
}
