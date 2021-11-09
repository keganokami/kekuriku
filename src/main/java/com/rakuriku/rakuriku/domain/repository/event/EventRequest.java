package com.rakuriku.rakuriku.repository.event;

@Data
@RequiredArgsConstructor
public class EventRequest {
	private String eventId;
	private String eventName;
	private String eventCategory;
}
