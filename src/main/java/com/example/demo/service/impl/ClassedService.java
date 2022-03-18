package com.example.demo.service.impl;

import com.example.demo.model.Classed;
import com.example.demo.repository.IClassedRepository;
import com.example.demo.service.IClassedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassedService implements IClassedService {

    @Autowired
    private IClassedRepository iClassedRepository;

    @Override
    public Iterable<Classed> findAll() {
        return iClassedRepository.findAll();
    }

    @Override
    public Optional<Classed> findById(Long id) {
        return iClassedRepository.findById(id);
    }

    @Override
    public Classed save(Classed classed) {
        return iClassedRepository.save(classed);
    }

    @Override
    public void deleteById(Long id) {
        iClassedRepository.deleteById(id);
    }
}
