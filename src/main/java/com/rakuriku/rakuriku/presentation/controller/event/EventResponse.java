package com.rakuriku.rakuriku.presentation.controller.event;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EventResponse {
	private String eventId;
	private String eventName;
	private String eventCategory;
}
