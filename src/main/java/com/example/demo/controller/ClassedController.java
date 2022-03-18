package com.example.demo.controller;

import com.example.demo.model.Classed;
import com.example.demo.model.Student;
import com.example.demo.service.IClassedService;
import com.example.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping(value = "/classed")
public class ClassedController {

    @Autowired
    private IStudentService iStudentService;

    @Autowired
    private IClassedService iClassedService;

    @ModelAttribute(name = "classes")
    private Iterable<Classed> findAdd() {
        return iClassedService.findAll();
    }

    @GetMapping
    public ModelAndView showListClass() {
        return new ModelAndView("classed/list-class");
    }

}
