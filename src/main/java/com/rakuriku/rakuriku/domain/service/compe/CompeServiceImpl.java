package com.rakuriku.rakuriku.domain.service.compe;

import com.rakuriku.rakuriku.entities.compe.CompeEntity;
import com.rakuriku.rakuriku.repository.compe.CompeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompeServiceImpl implements CompeService {

	@Autowired
	private final CompeRepository compeRepository;

	@Override
	public void createCompe(CompeEntity compe) {
		compeRepository.save(compe);
	}
	
}
