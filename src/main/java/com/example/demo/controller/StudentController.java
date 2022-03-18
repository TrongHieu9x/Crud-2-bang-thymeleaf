package com.example.demo.controller;

import com.example.demo.model.Classed;
import com.example.demo.model.Student;
import com.example.demo.service.IClassedService;
import com.example.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService iStudentService;

    @Autowired
    private IClassedService iClassedService;

    @ModelAttribute(name = "classes")
    private Iterable<Classed> findAdd() {
        return iClassedService.findAll();
    }

    @GetMapping
    public ModelAndView showAll(@PageableDefault(value = 3) Pageable pageable,
                                @RequestParam Optional<String> search,
                                Optional<Double> min, Optional<Double> max) {
        ModelAndView modelAndView = new ModelAndView("student/list");
        Page<Student> students;
        if (search.isPresent()) {
            students = iStudentService.findByAvgBetweenAndName(
                    pageable, search.get(), min.orElse(1d), max.orElse(10d));
            modelAndView.addObject("search", search.get());
            modelAndView.addObject("min", min.orElse(1d));
            modelAndView.addObject("max", max.orElse(10d));
        } else {
            students = iStudentService.findAll(pageable);
        }
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView showDetail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("student/detail");
        Optional<Student> student = iStudentService.findById(id);
        student.ifPresent(value -> modelAndView.addObject("student", value));
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PageableDefault(value = 3) Pageable pageable, @PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("student/list");
        iStudentService.deleteById(id);
        Page<Student> students = iStudentService.findAll(pageable);
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("student/create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createStudent(@ModelAttribute("student") Student student,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("student", student);
            return "student/create";
        }
        iStudentService.save(student);
        return "redirect:/student";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("student/edit");
        Optional<Student> student = iStudentService.findById(id);
        student.ifPresent(value -> modelAndView.addObject("student", value));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String editStudent(@ModelAttribute("student") Student student,
                              BindingResult bindingResult, Model model,
                              @PathVariable("id") Long id) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("student", student);
            return "student/edit";
        }
        student.setId(id);
        iStudentService.save(student);
        return "redirect:/student";
    }

    @GetMapping("/desc")
    public ModelAndView sortByDesc(@SortDefault(sort = "avg", direction = Sort.Direction.DESC)
                                   @PageableDefault(value = 10) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("student/list");
        Page<Student> students = iStudentService.findAll(pageable);
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/asc")
    public ModelAndView sortByAsc(@SortDefault(sort = "avg", direction = Sort.Direction.ASC)
                                  @PageableDefault(value = 10) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("student/list");
        Page<Student> students = iStudentService.findAll(pageable);
        modelAndView.addObject("students", students);
        return modelAndView;
    }
}
