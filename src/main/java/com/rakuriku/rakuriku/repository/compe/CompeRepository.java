package com.rakuriku.rakuriku.repository.compe;


import com.rakuriku.rakuriku.entities.compe.CompeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompeRepository extends CrudRepository<CompeEntity, String> {
	
}
