package com.example.demo.service;

import com.example.demo.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService extends IGeneralService<Student> {

    Page<Student> findAll(Pageable pageable);

//    Page<Student> findByAvgBetween(Pageable pageable, String search, double min, double max);

    Page<Student> findByAvgBetweenAndName(Pageable pageable, String search, double min, double max);
}
