package com.example.repository;

import com.example.domain.Note;
import com.example.service.NoteService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findAllByOrderByCreatedAtDesc();
}
