package com.rakuriku.rakuriku.domain.repository.event;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EventRequest {
	private String eventId;
	private String eventName;
	private String eventCategory;
}
