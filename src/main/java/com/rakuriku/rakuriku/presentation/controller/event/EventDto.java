package com.rakuriku.rakuriku.presentation.controller.event;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto implements Serializable {
	private String eventId;
	private String eventName;
	private String eventCategory;
	private Integer participationFee;
}
