package com.minhaempresa.services;

import com.minhaempresa.controllers.response.GetClienteComContasResponse;
import com.minhaempresa.controllers.response.GetContaSemTitularResponse;
import com.minhaempresa.model.Cliente;
import com.minhaempresa.model.Conta;
import com.minhaempresa.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public List<GetClienteComContasResponse> buscarTitulares() {
        List<Cliente> listaTitulares = clienteRepository.findAll();
        List<GetClienteComContasResponse> listaTitularesResponse = new ArrayList<>();
        for (Cliente cliente : listaTitulares) {
            listaTitularesResponse.add(buscarContas(cliente.getId()));
        }
        return listaTitularesResponse;
    }

    public Cliente buscarPorId(Long id) {
        Optional<Cliente> titular = clienteRepository.findById(id);
        return titular.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public GetClienteComContasResponse buscarContas(Long id) {
        List<Conta> contas = clienteRepository.buscarContasPorTitular(id);
        Cliente cliente = buscarPorId(id);
        return associarTitularComContas(contas, cliente);
    }

    private GetClienteComContasResponse associarTitularComContas(List<Conta> contas, Cliente cliente) {
        List<GetContaSemTitularResponse> listaContas = contas.stream()
                .map(c -> new GetContaSemTitularResponse(c.getTipo(), c.getNumeroConta()))
                .collect(Collectors.toList());
        return new GetClienteComContasResponse(cliente.getId(), cliente.getNome(), listaContas);
    }

}
