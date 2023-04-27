package com.minhaempresa.controllers;

import com.minhaempresa.model.Conta;
import com.minhaempresa.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController<T> {
    @Autowired
    private ContaRepository<Conta> repository;

    @GetMapping
    public List<Conta> findAll() {
        return repository.findAll();
    }

}
