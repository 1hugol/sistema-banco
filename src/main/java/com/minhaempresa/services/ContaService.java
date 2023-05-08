package com.minhaempresa.services;

import com.minhaempresa.controllers.request.PostContaRequest;
import com.minhaempresa.controllers.response.GetContaComSaldoResponse;
import com.minhaempresa.controllers.response.GetContaComTitularResponse;
import com.minhaempresa.model.Cliente;
import com.minhaempresa.model.Conta;
import com.minhaempresa.model.ContaCorrente;
import com.minhaempresa.model.ContaPoupanca;
import com.minhaempresa.repositories.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ContaService {
    private final ContaRepository contaRepository;

    private final ClienteService clienteService;

    public List<GetContaComTitularResponse> buscarContas() {
        return retornarListaDeContas();
    }

    public GetContaComSaldoResponse buscarConta(Long id) {
        GetContaComSaldoResponse contaResponse;
        Optional<Conta> conta = contaRepository.findById(id);
        if (conta.isPresent()) {
            contaResponse = retornarContaComSaldo(conta.get());
        } else throw new EntityNotFoundException("Conta não encontrada");
        return contaResponse;
    }

    public GetContaComTitularResponse criarConta(PostContaRequest postContaRequest) {
        Conta conta = contaRepository.save(associarContaComTitular(postContaRequest));
        return retornarContaCriada(conta);
    }

    public GetContaComSaldoResponse depositar(Long id, BigDecimal valor) {
        Optional<Conta> conta = contaRepository.findById(id);
        if (conta.isPresent()) {
            conta.get().depositar(valor);
        } else throw new EntityNotFoundException("Conta não encontrada");

        return retornarContaComSaldo(contaRepository.save(conta.get()));
    }

    public GetContaComSaldoResponse sacar(Long id, BigDecimal valor) {
        Optional<Conta> conta = contaRepository.findById(id);
        if (conta.isPresent()) {
            conta.get().sacar(valor);
        } else throw new EntityNotFoundException("Conta não encontrada");

        return retornarContaComSaldo(contaRepository.save(conta.get()));
    }

    private Conta associarContaComTitular(PostContaRequest postContaRequest) {

        Cliente titular = clienteService.buscarPorId(postContaRequest.getTitular_id());
        Conta novaConta = null;

        if (postContaRequest.getTipo().equals("CC")) {
            if (postContaRequest.getSaldo().equals(BigDecimal.ZERO)) {
                novaConta =
                        new ContaCorrente(null
                                , postContaRequest.getDataCriacao()
                                , titular);
            } else {
                novaConta =
                        new ContaCorrente(null
                                , postContaRequest.getSaldo()
                                , postContaRequest.getDataCriacao()
                                , titular);
            }
        } else if (postContaRequest.getTipo().equals("CP")) {
            novaConta = new ContaPoupanca(null
                    , postContaRequest.getSaldo()
                    , postContaRequest.getDataCriacao()
                    , titular);
        }
        return novaConta;
    }

    private List<GetContaComTitularResponse> retornarListaDeContas() {
        List<Conta> listaContas = contaRepository.findAll();
        return listaContas
                .stream()
                .map(c -> new GetContaComTitularResponse(c.getTipo(), c.getNumeroConta(), c.getTitular().getNome()))
                .collect(Collectors.toList());
    }

    private GetContaComTitularResponse retornarContaCriada(Conta conta) {
        return new GetContaComTitularResponse(conta.getTipo(), conta.getNumeroConta(), conta.getTitular().getNome());
    }

    private GetContaComSaldoResponse retornarContaComSaldo(Conta conta) {
        return new GetContaComSaldoResponse(conta.getTitular().getNome(), conta.getTipo(), conta.getNumeroConta(), conta.getSaldo());
    }
}
