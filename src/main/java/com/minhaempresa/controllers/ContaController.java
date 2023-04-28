package com.minhaempresa.controllers;

import com.minhaempresa.model.Conta;
import com.minhaempresa.services.ContaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/contas")
public class ContaController<T> {

    private final ContaService<Conta> contaService;

    @GetMapping
    public ResponseEntity<List<Conta>> buscarTodasAsContas() {
        return ResponseEntity.status(HttpStatus.OK).body(contaService.buscarContas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarContaPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(contaService.buscarConta(id));
    }

    @PutMapping("/sacar/{id}")
    public ResponseEntity<Conta> realizarSaqueConta(@PathVariable Long id, @RequestParam BigDecimal valor) {
        return ResponseEntity.status(HttpStatus.OK).body(contaService.sacar(id, valor));
    }

}
