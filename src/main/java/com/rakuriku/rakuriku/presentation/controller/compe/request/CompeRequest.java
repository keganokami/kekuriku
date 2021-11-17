package com.rakuriku.rakuriku.presentation.controller.compe.request;

import lombok.Data;

@Data
public class CompeRequest {
	private String adminId;
	private String compeName;
	private String compeDates;
	private String compePlace;
	private String compeGuidelinesFile;
	private String compeEvent;
}
