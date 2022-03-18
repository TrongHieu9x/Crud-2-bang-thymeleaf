package com.example.demo.repository;

import com.example.demo.model.Classed;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IClassedRepository extends PagingAndSortingRepository<Classed, Long> {
}
