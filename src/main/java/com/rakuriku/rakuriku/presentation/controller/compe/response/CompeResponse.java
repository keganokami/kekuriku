package com.rakuriku.rakuriku.presentation.controller.compe.response;

import java.io.Serializable;

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
	private String compeFeeType;
	private Serializable compeEvent;
}
