package com.rakuriku.rakuriku.presentation.controller.compe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuriku.rakuriku.domain.model.auth.AdminsEntity;
import com.rakuriku.rakuriku.domain.model.compe.CompeEntity;
import com.rakuriku.rakuriku.domain.model.compe.EntriesEntity;
import com.rakuriku.rakuriku.presentation.controller.compe.request.CompeRequest;
import com.rakuriku.rakuriku.presentation.controller.compe.request.EntryCompeRequest;
import com.rakuriku.rakuriku.presentation.controller.compe.request.EntryEvent;
import com.rakuriku.rakuriku.presentation.controller.compe.response.CompeResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompeFactory {

    @Autowired
    private ModelMapper modelMapper;

    public CompeEntity createCompeEntity(String adminId, CompeRequest request) throws JsonProcessingException {
        CompeEntity compeEntity = new CompeEntity();

		AdminsEntity adminsEntity = new AdminsEntity();
		adminsEntity.setAdminId(adminId);
		compeEntity.setAdmins(adminsEntity);
		compeEntity.setCompeId(UUID.randomUUID().toString());
		compeEntity.setCompeName(request.getCompeName());
		compeEntity.setCompeDates(request.getCompeDates());
		compeEntity.setCompePlace(request.getCompePlace());

		ObjectMapper objectMapper = new ObjectMapper();
        compeEntity.setCompeEvent(objectMapper.writeValueAsString(request.getCompeEvent()));

        return compeEntity;
    }

    public EntriesEntity createEntriesEntity(EntryCompeRequest request) {
        EntriesEntity entriesEntity = new EntriesEntity();
        entriesEntity.setName(request.getName());
        entriesEntity.setNameKana(request.getNameKana());
        entriesEntity.setPhoneNumber(request.getPhoneNum());
        entriesEntity.setSex(request.getSex());
        entriesEntity.setNumber("001");
        return entriesEntity;
    }

    public List<CompeResponse> createCompeResponse(List<CompeEntity> compeEntities) {
        return map(compeEntities, CompeResponse.class);
    }

    public CompeResponse createCompeResponse(CompeEntity compeEntity) {
        return map(compeEntity, CompeResponse.class);
    }

    // TODO 切り出す
    public <D> D map(Object src, Class<D> dstType) {
        return modelMapper.map(src, dstType);
    }

    public <D> List<D> map(List<?> srcs, Class<D> dstType) {
        return srcs.stream().map(src -> map(src, dstType)).collect(Collectors.toList());
    }
}
