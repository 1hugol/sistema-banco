package com.minhaempresa.controllers;

import com.minhaempresa.controllers.response.GetClienteComContasResponse;
import com.minhaempresa.model.Cliente;
import com.minhaempresa.services.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/titulares")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<GetClienteComContasResponse>> buscarTodosOsTitulares() {
        return ResponseEntity.ok().body(clienteService.buscarTitulares());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> buscarTitularPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(clienteService.buscarPorId(id));
    }

    @GetMapping(value = "/{id}/contas")
    public ResponseEntity<GetClienteComContasResponse> buscarContasDoTitular(@PathVariable Long id) {
        return ResponseEntity.ok().body(clienteService.buscarContas(id));
    }
}
