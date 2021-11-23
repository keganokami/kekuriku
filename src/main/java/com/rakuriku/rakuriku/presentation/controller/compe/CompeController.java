package com.rakuriku.rakuriku.presentation.controller.compe;

import java.util.UUID;
import javax.annotation.PostConstruct;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuriku.rakuriku.domain.model.auth.AdminsEntity;
import com.rakuriku.rakuriku.domain.model.compe.CompeEntity;
import com.rakuriku.rakuriku.domain.service.compe.CompeService;
import com.rakuriku.rakuriku.presentation.controller.event.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/compe")
@RequiredArgsConstructor
public class CompeController {

	@Autowired
	private final CompeService compeService;

	@PostConstruct
    private void init() {
        createCompe();
    }

	@PostMapping("/new")
	public void createCompe() {

		CompeEntity compeEntity = new CompeEntity();

		AdminsEntity adminsEntity = new AdminsEntity();
		adminsEntity.setUserId("admin001");
		compeEntity.setAdmins(adminsEntity);
		compeEntity.setCompeId(UUID.randomUUID().toString());
		compeEntity.setCompeName("compeName");
		compeEntity.setCompeDates("compeDates");
		compeEntity.setCompePlace("compePlace");

		EventDto event = new EventDto();
		event.setEventId("eventId");
		event.setEventName("eventName");
		event.setEventCategory("eventCategory");

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			compeEntity.setCompeEvent(objectMapper.writeValueAsString(event));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		compeService.createCompe(compeEntity);
	}
	
}
