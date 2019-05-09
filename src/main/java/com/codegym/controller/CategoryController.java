package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("category/create-form");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }
    @PostMapping("/create-category")
    public ModelAndView createCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("category/create-form");
        return modelAndView;
    }

    // list category

    @GetMapping("/list-category")
    public ModelAndView showListCategory(){
        Iterable<Category> listCategory =  categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/category/list-category");
        modelAndView.addObject("listCategory", listCategory);
        return modelAndView;
    }

    // eidt category

    @GetMapping("/edit-category/{id}")
    public ModelAndView editCategory(@PathVariable("id") Long id){
        Category category = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/category/edit-form");
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    @PostMapping("/edit-category")
    public String editCategory(@ModelAttribute("category") Category category, RedirectAttributes redirect){
        categoryService.save(category);
        return "redirect:/list-category";
    }

    @GetMapping("delete-category/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        categoryService.remove(id);
        return "redirect:/list-category";
    }


}
