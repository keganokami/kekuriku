package com.rakuriku.rakuriku.domain.repository.compe;

import com.rakuriku.rakuriku.domain.model.compe.EntriesEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntriesRepository extends CrudRepository<EntriesEntity, String> {
}
