package com.rakuriku.rakuriku.domain.service.compe;

import java.util.List;

import com.rakuriku.rakuriku.domain.model.compe.CompeEntity;
import com.rakuriku.rakuriku.domain.model.compe.CompeEntryManagementEntity;
import com.rakuriku.rakuriku.domain.model.compe.EntriesEntity;
import com.rakuriku.rakuriku.domain.repository.compe.CompeRepository;
import com.rakuriku.rakuriku.presentation.controller.compe.EntryEventDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompeServiceImpl implements CompeService {

	@Autowired
	private final CompeRepository compeRepository;

	@Override
	public CompeEntity createCompe(CompeEntity compe) {
		compeRepository.save(compe);
		return compeRepository.findById(compe.getCompeId()).orElse(null);
	}

	@Override
	public List<CompeEntity> getCompes(String adminId) {
		return compeRepository.findWithCompeByAdminId(adminId);
	}

	@Override
	public CompeEntity getCompe(String compeId) {
		return compeRepository.findWithCompeByCompeId(compeId);
	}

	@Override
	public CompeEntryManagementEntity entryCompe(EntriesEntity entriesEntity, List<EntryEventDto> list) {
		
		return null;
	}
	
}
