package com.codegym.repository;

import com.codegym.model.Category;
import com.codegym.model.Note;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {
    Iterable<Note> findAllByCategory(Category category);
}
