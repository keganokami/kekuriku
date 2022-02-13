package com.rakuriku.rakuriku.presentation.controller.compe.request;

import java.util.List;

import com.rakuriku.rakuriku.presentation.controller.event.EventDto;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompeRequest {
	private String adminId;
	private String compeName;
	private String compeDates;
	private String compePlace;
	private MultipartFile compeGuidelinesFile;
	private String compeFeeType;
	private Integer compeParticipationFee;
	private boolean settingMaxParticipation;
	private Integer compeMaxParticipation;
	private List<EventDto> compeEvent;
}
