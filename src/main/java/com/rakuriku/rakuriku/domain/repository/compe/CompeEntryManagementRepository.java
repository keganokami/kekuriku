package com.rakuriku.rakuriku.domain.repository.compe;

import com.rakuriku.rakuriku.domain.model.compe.CompeEntryManagementEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompeEntryManagementRepository extends CrudRepository<CompeEntryManagementEntity, String> {
    
}
