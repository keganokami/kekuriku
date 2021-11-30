package com.rakuriku.rakuriku.domain.repository.compe;


import java.util.List;

import com.rakuriku.rakuriku.domain.model.compe.CompeEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompeRepository extends CrudRepository<CompeEntity, String> {
    @Query(
        value = 
        "SELECT "
        + "DISTINCT compe "
        + "FROM CompeEntity compe "
        + "LEFT JOIN FETCH compe.admins admins "
        + "WHERE "
        + "admins.id = :adminId"
    )
	List<CompeEntity> findWithCompeByAdminId(@Param("adminId") String adminId);

    CompeEntity findWithCompeByCompeId(String compeId);
}
