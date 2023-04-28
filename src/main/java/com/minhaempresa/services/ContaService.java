package com.minhaempresa.services;

import com.minhaempresa.model.Conta;
import com.minhaempresa.repositories.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ContaService<T> {
    private final ContaRepository<Conta> contaRepository;

    public List<Conta> buscarContas() {
        return contaRepository.findAll();
    }

    public Conta buscarConta(Long id) {
        Optional<Conta> obj = contaRepository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    public Conta sacar(Long id, BigDecimal valor) {
        Conta conta = buscarConta(id);
        conta.sacar(valor);
        return contaRepository.save(conta);
    }

}
