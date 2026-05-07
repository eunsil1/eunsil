package com.example.crud1.repository;

import com.example.crud1.entity.DoIt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoRepository extends CrudRepository<DoIt, Long> {
    @Override
    List<DoIt> findAll();
}
