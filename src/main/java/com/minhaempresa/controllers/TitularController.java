package com.minhaempresa.controllers;

import com.minhaempresa.controllers.response.TitularResponse;
import com.minhaempresa.model.Titular;
import com.minhaempresa.services.TitularService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/titulares")
public class TitularController {

    private final TitularService titularService;

    @GetMapping
    public ResponseEntity<List<TitularResponse>> buscarTodosOsTitulares() {
        return ResponseEntity.status(HttpStatus.OK).body(titularService.buscarTitulares());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Titular> buscarTitularPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(titularService.buscarPorId(id));
    }

    @GetMapping(value = "/{id}/contas")
    public ResponseEntity<TitularResponse> buscarContasDoTitular(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(titularService.buscarContas(id));
    }
}
