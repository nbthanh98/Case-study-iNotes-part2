package com.codegym.service;

import com.codegym.model.Category;
import com.codegym.model.Note;
import org.springframework.data.domain.Page;

public interface NoteService  {
    Iterable<Note> findAll();
    Note findById(Long id);
    void save(Note note);
    void remove(Long id);
    Iterable<Note> findAllByCategory(Category category);
}
