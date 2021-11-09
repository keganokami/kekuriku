package com.rakuriku.rakuriku.presentation.controller.compe;

import com.rakuriku.rakuriku.domain.service.compe.CompeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("compe")
@RequiredArgsConstructor
public class CompeController {

	@Autowired
	private final CompeService compeService;

	@PostMapping("/new")
	public void createCompe() {

	}
	
}
