package com.pfe_app.eya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe_app.eya.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

	List<Ticket> findAllByUserId(Long employeeId);
	
}
