package com.pfe_app.eya.dto;

import lombok.Data;

@Data
public class SingleTicketDto {
	
	private TicketDto ticketDto;

	public TicketDto getTicketDto() {
		return ticketDto;
	}

	public void setTicketDto(TicketDto ticketDto) {
		this.ticketDto = ticketDto;
	}

}
