package com.minhaempresa.services;

import com.minhaempresa.controllers.response.GetClienteComContasResponse;
import com.minhaempresa.controllers.response.GetContaSemTitularResponse;
import com.minhaempresa.model.Cliente;
import com.minhaempresa.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public List<GetClienteComContasResponse> buscarTitulares() {
        List<Cliente> listaTitulares = clienteRepository.findAll();
        List<GetClienteComContasResponse> listaTitularesResponse = new ArrayList<>();

        for (Cliente cliente : listaTitulares) {
            listaTitularesResponse.add(buscarContasTitularId(cliente.getId()));
        }
        return listaTitularesResponse;
    }

    public Cliente buscarPorId(Long id) {
        Optional<Cliente> titular = clienteRepository.findById(id);
        return titular.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    public Cliente buscarPorCpf(String cpf) {
        Cliente titular = clienteRepository.findByCpf(cpf);
        if (titular == null) throw new EntityNotFoundException("Cliente não encontrado");
        return titular;
    }

    public GetClienteComContasResponse buscarContasTitularId(Long id) {
        List<GetContaSemTitularResponse> contas = clienteRepository.buscarContasPorIdTitular(id);
        Cliente cliente = buscarPorId(id);
        return associarTitularComContas(contas, cliente);
    }

    public GetClienteComContasResponse buscarContasTitularCpf(String cpf) {
        List<GetContaSemTitularResponse> contas = clienteRepository.buscarContasPorCpfTitular(cpf);
        Cliente cliente = buscarPorCpf(cpf);
        return associarTitularComContas(contas, cliente);
    }

    private GetClienteComContasResponse associarTitularComContas(List<GetContaSemTitularResponse> contas, Cliente cliente) {
        return new GetClienteComContasResponse(cliente.getId(), cliente.getNome(), contas);
    }
}
