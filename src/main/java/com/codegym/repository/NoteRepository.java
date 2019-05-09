package com.codegym.repository;

import com.codegym.model.Category;
import com.codegym.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {
    Iterable<Note> findAllByCategory(Category category);

    Page<Note> findAllByCategory(String category ,Pageable pageable);
}
