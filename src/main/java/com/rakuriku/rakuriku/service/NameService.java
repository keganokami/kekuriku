package com.rakuriku.rakuriku.service;

import java.util.List;

import com.rakuriku.rakuriku.entities.NameEntity;
import com.rakuriku.rakuriku.repository.NameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NameService {
    @Autowired
    private NameRepository repo;

    public List<NameEntity> getAll() {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(repo.findAll());
        return repo.findAll();
    }
}
