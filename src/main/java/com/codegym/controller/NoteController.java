package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Note;
import com.codegym.service.CategoryService;
import com.codegym.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class NoteController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private NoteService noteService;

    @GetMapping("/")
    public String showCreateNoteForm(Model model){
        model.addAttribute("note", new Note());
//        Iterable<Category> listCategory = categoryService.findAll();
//        model.addAttribute("listCategory", listCategory);
        return "/note/create-form";
    }

    @PostMapping("/create-note")
    public String createNote(@ModelAttribute("note") Note note, Model model){
        noteService.save(note);
        return "/note/create-form";
    }
    // list note

    @GetMapping("/list-note")
    public ModelAndView listNote(@RequestParam("category")Optional<String> category , Pageable pageable){
        Page<Note> listNote;
        if(category.isPresent()){
                listNote = noteService.findallByCategory(category.get(), pageable);
        }else{
            listNote = noteService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/note/list-note");
        modelAndView.addObject("listNote", listNote);
        return modelAndView;
    }

    // edit
    @GetMapping("/edit-note/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id){
       Note note = noteService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/note/edit-form");
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @PostMapping("/edit-note")
    public String editNote(Model model, @ModelAttribute("note") Note note, RedirectAttributes redirect){
        noteService.save(note);
        return "redirect:/list-note";
    }

    @GetMapping("/delete-note/{id}")
    public String deleteNote(@PathVariable("id") Long id, RedirectAttributes redirect){
        noteService.remove(id);
        return "redirect:/list-note";
    }

    // show all note in category

    @GetMapping("/noteInCategory/{id}")
    public ModelAndView showAllNoteInCategory(@PathVariable("id") Long id){
        Category category = categoryService.findById(id);
        if( category != null) {
            Iterable<Note> listNoteINCategory = noteService.findAllByCategory(category);
            ModelAndView modelAndView = new ModelAndView("/note/list-note-category");
            modelAndView.addObject("listNoteINCategory", listNoteINCategory);
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("error404");
        return modelAndView;
    }

    @ModelAttribute("listCategory")
    public Iterable<Category> listCategory(){
        return categoryService.findAll();
    }

}
