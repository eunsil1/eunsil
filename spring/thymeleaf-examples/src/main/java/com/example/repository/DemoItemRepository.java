package com.example.repository;

import com.example.domain.DemoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemoItemRepository extends JpaRepository<DemoItem, Long> {

    List<DemoItem> findAllByOrderByIdAsc();
}
