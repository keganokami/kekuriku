package com.rakuriku.rakuriku.presentation.controller.compe;

import java.util.UUID;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuriku.rakuriku.domain.model.auth.AdminsEntity;
import com.rakuriku.rakuriku.domain.model.compe.CompeEntity;
import com.rakuriku.rakuriku.domain.service.compe.CompeService;
import com.rakuriku.rakuriku.presentation.controller.compe.request.CompeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/compe")
@RequiredArgsConstructor
public class CompeController {

	@Autowired
	private final CompeService compeService;

	@PostMapping("/new")
	public void createCompe(@RequestBody CompeRequest request) {

		CompeEntity compeEntity = new CompeEntity();

		AdminsEntity adminsEntity = new AdminsEntity();
		adminsEntity.setUserId(request.getAdminId());
		compeEntity.setAdmins(adminsEntity);
		compeEntity.setCompeId(UUID.randomUUID().toString());
		compeEntity.setCompeName(request.getCompeName());
		compeEntity.setCompeDates(request.getCompeDates());
		compeEntity.setCompePlace(request.getCompePlace());

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			compeEntity.setCompeEvent(objectMapper.writeValueAsString(request.getCompeEvent()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		compeService.createCompe(compeEntity);
	}
	
}
