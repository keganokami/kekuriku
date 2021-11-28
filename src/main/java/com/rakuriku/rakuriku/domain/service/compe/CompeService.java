package com.rakuriku.rakuriku.domain.service.compe;

import java.util.List;

import com.rakuriku.rakuriku.domain.model.compe.CompeEntity;

public interface CompeService {
	CompeEntity createCompe(CompeEntity compe);

	List<CompeEntity> getCompes(String adminId);

}
