package com.rakuriku.rakuriku.domain.service.compe;

import java.util.List;

import com.rakuriku.rakuriku.domain.model.compe.CompeEntity;
import com.rakuriku.rakuriku.domain.model.compe.CompeEntryManagementEntity;
import com.rakuriku.rakuriku.domain.model.compe.EntriesEntity;
import com.rakuriku.rakuriku.domain.repository.compe.CompeEntryManagementRepository;
import com.rakuriku.rakuriku.domain.repository.compe.CompeRepository;
import com.rakuriku.rakuriku.domain.repository.compe.EntriesRepository;
import com.rakuriku.rakuriku.presentation.controller.compe.EntryEventDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CompeServiceImpl implements CompeService {

	private final CompeRepository compeRepository;
	private final EntriesRepository entriesRepository;
	private final CompeEntryManagementRepository compeEntryManagementRepository;

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
	public CompeEntryManagementEntity entryCompe(String compeId, EntriesEntity entriesEntity, List<EntryEventDto> list) {
		String entryId = entriesEntity.getId();
		entriesRepository.save(entriesEntity);

		return null;
	}
	
}
