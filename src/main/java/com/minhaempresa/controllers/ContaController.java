package com.minhaempresa.controllers;

import com.minhaempresa.controllers.request.PostContaRequest;
import com.minhaempresa.controllers.response.GetContaComSaldoResponse;
import com.minhaempresa.controllers.response.GetContaComTitularResponse;
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
public class ContaController {

    private final ContaService contaService;

    @GetMapping
    public ResponseEntity<List<GetContaComTitularResponse>> buscarTodasAsContas() {
        return ResponseEntity.ok().body(contaService.buscarContas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetContaComSaldoResponse> buscarContaPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(contaService.buscarConta(id));
    }

    @PostMapping
    public ResponseEntity<GetContaComTitularResponse> criarConta(@RequestBody PostContaRequest postContaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contaService.criarConta(postContaRequest));
    }

    @PutMapping("/depositar/{id}")
    public ResponseEntity<GetContaComSaldoResponse> realizarDepositoConta(@PathVariable Long id, @RequestParam BigDecimal valor) {
        return ResponseEntity.ok().body(contaService.depositar(id, valor));
    }

    @PutMapping("/sacar/{id}")
    public ResponseEntity<GetContaComSaldoResponse> realizarSaqueConta(@PathVariable Long id, @RequestParam BigDecimal valor) {
        return ResponseEntity.ok().body(contaService.sacar(id, valor));
    }
}
