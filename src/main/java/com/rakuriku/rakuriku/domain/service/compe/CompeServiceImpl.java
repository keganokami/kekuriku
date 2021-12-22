package com.rakuriku.rakuriku.domain.service.compe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.rakuriku.rakuriku.domain.model.compe.CompeEntity;
import com.rakuriku.rakuriku.domain.model.compe.CompeEntryManagementEntity;
import com.rakuriku.rakuriku.domain.model.compe.EntriesEntity;
import com.rakuriku.rakuriku.domain.repository.compe.CompeEntryManagementRepository;
import com.rakuriku.rakuriku.domain.repository.compe.CompeRepository;
import com.rakuriku.rakuriku.domain.repository.compe.EntriesRepository;
import com.rakuriku.rakuriku.presentation.controller.compe.EntryEventDto;

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
	public CompeEntryManagementEntity entryCompe(String compeId, EntriesEntity entriesEntity,
			List<EntryEventDto> entryEvents) {
		entriesRepository.save(entriesEntity);

		CompeEntity compe = compeRepository.findWithCompeByCompeId(compeId);
		String adminId = compe.getAdmins().getAdminId();
		String entryId = entriesEntity.getId();
		List<CompeEntryManagementEntity> cManagementEntities = new ArrayList<>();
		for (EntryEventDto event : entryEvents) {
			CompeEntryManagementEntity manageEntity = new CompeEntryManagementEntity();
			manageEntity.setId(UUID.randomUUID().toString());
			manageEntity.setRoundId("roundId");
			manageEntity.setEventId(event.getEventId());
			manageEntity.setAdminId(adminId);
			
			if (!Objects.isNull(event.getEventRecode())) {
				String recode = event.getEventRecode();
				Integer recodeInt = Integer.valueOf(recode.replace(".", ""));
				manageEntity.setApplicationRecordInt(recodeInt);
				manageEntity.setApplicationRecordDisplay(recode);
			}
			
			EntriesEntity entity = new EntriesEntity();
			entity.setId(entryId);
			manageEntity.setEntries(entity);
			CompeEntity compeEntity = new CompeEntity();
			compeEntity.setCompeId(compeId);
			manageEntity.setCompe(compeEntity);
			manageEntity.setCanGoToNext("0");
			cManagementEntities.add(manageEntity);
		}

		compeEntryManagementRepository.saveAll(cManagementEntities);
		

		return null;
	}

}
