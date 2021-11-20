package com.rakuriku.rakuriku.presentation.controller.compe.request;

import com.rakuriku.rakuriku.presentation.controller.event.EventDto;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class CompeRequest {
	private String adminId;
	private String compeName;
	private String compeDates;
	private String compePlace;
	private MultipartFile compeGuidelinesFile;
	private EventDto compeEvent;
}
