package com.rakuriku.rakuriku.repository;

import com.rakuriku.rakuriku.entities.NameEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameRepository extends JpaRepository<NameEntity, Integer> {

}
