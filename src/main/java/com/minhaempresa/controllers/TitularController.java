package com.minhaempresa.controllers;

import com.minhaempresa.model.Titular;
import com.minhaempresa.repositories.TitularRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/titulares")
public class TitularController {

    private final TitularRepository repository;

    public TitularController(TitularRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Titular> findAll() {
        return repository.findAll();
    }
}
