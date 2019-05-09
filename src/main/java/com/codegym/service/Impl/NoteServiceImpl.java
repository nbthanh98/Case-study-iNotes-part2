package com.codegym.service.Impl;

import com.codegym.model.Category;
import com.codegym.model.Note;
import com.codegym.repository.NoteRepository;
import com.codegym.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;

public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;


    @Override
    public Iterable<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note findById(Long id) {
        return noteRepository.findOne(id);
    }

    @Override
    public void save(Note note) {
        noteRepository.save(note);
    }

    @Override
    public Iterable<Note> findAllByCategory(Category category) {
        return noteRepository.findAllByCategory(category);
    }

    @Override
    public void remove(Long id) {
        noteRepository.delete(id);
    }
}
