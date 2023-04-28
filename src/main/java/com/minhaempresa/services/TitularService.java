package com.minhaempresa.services;

import com.minhaempresa.controllers.response.ContaResponse;
import com.minhaempresa.controllers.response.TitularResponse;
import com.minhaempresa.model.Conta;
import com.minhaempresa.model.Titular;
import com.minhaempresa.repositories.TitularRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TitularService {
    private final TitularRepository titularRepository;

    public List<TitularResponse> buscarTitulares() {
        List<Titular> listaTitulares = titularRepository.findAll();
        List<TitularResponse> listaTitularesResponse = new ArrayList<>();
        for (Titular titular: listaTitulares) {
            listaTitularesResponse.add(buscarContas(titular.getId()));
        }
        return listaTitularesResponse;
    }

    public Titular buscarPorId(Long id) {
        Optional<Titular> titular = titularRepository.findById(id);
        return titular.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public TitularResponse buscarContas(Long id) {
        List<Conta> contas = titularRepository.buscarContasPorTitular(id);
        Titular titular = buscarPorId(id);
        return associarTitularComContas(contas, titular);
    }

    private TitularResponse associarTitularComContas(List<Conta> contas,Titular titular) {
        List<ContaResponse> listaContas = contas.stream()
                .map(c -> new ContaResponse(c.getTipo(), c.getNumeroConta()))
                .collect(Collectors.toList());
        return new TitularResponse(titular.getId(), titular.getNome(), listaContas);
    }

}
