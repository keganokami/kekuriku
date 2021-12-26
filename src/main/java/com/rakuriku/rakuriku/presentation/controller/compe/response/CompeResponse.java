package com.rakuriku.rakuriku.presentation.controller.compe.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rakuriku.rakuriku.presentation.controller.event.EventDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompeResponse {
    private String compeId;
	private String compeName;
	private String compeDates;
	private String compePlace;
	private Serializable compeEvent;
	@JsonIgnore private List<EventDto> compeEventList;

	@JsonProperty("totalParticipationFee")
	public Integer getTotalParticipationFee() {
		long count = compeEventList.stream().map(list -> list.getParticipationFee()).count();
		return 0;
	}
}
