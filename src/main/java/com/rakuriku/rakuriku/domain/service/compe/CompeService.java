package com.rakuriku.rakuriku.domain.service.compe;

import java.util.List;

import com.rakuriku.rakuriku.domain.model.compe.CompeEntity;
import com.rakuriku.rakuriku.domain.model.compe.CompeEntryManagementEntity;
import com.rakuriku.rakuriku.domain.model.compe.EntriesEntity;
import com.rakuriku.rakuriku.presentation.controller.compe.EntryEventDto;

public interface CompeService {
	CompeEntity createCompe(CompeEntity compe);

	List<CompeEntity> getCompes(String adminId);

	CompeEntity getCompe(String compeId);

	CompeEntryManagementEntity entryCompe(String compeId, EntriesEntity entriesEntity, List<EntryEventDto> list);

}
