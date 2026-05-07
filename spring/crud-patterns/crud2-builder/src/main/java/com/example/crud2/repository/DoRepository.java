package com.example.crud2.repository;

import com.example.crud2.entity.DoIt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoRepository extends CrudRepository<DoIt, Long> {
    @Override
    List<DoIt> findAll();
}
