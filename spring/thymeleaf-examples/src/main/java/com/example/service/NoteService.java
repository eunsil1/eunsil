package com.example.service;

import com.example.domain.Note;
import com.example.repository.NoteRepository;
import com.example.web.dto.NoteForm;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Transactional
    public Note create(NoteForm form) {
        Note note = Note.create(form.getTitle().trim(), form.getContent().trim());
        return noteRepository.save(note);
    }

    public Note findById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("메모를 찾을 수 없습니다. id="+ id));
    }

    public List<Note> findAll() {
        return noteRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public void update(Long id, @Valid NoteForm form) {
        Note note = findById(id);
        note.update(form.getTitle().trim(), form.getContent().trim());
        //@Transactional 하에 관리되므로 update 메서드만 실행시키면
        //jpa가 변경 감지하여 실제 레포지토리 데이터를 업데이트 해줌
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }
}
