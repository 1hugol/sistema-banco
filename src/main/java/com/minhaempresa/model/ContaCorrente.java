package com.minhaempresa.model;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.minhaempresa.utils.Constants.TAXA_SAQUE;

@NoArgsConstructor
@Entity
@DiscriminatorValue("CC")
public class ContaCorrente extends Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    public ContaCorrente(Long id, LocalDate dataCriacao, Cliente cliente) {
        super(id, "CC", dataCriacao, cliente);
    }

    public ContaCorrente(Long id, BigDecimal valor, LocalDate dataCriacao, Cliente titular) {
        super(id, "CC", valor, dataCriacao, titular);
    }

    @Override
    public void depositar(BigDecimal valor) {
        setSaldo(getSaldo().add(valor));
    }

    @Override
    public void sacar(BigDecimal valor) {
        valor = valor.add(TAXA_SAQUE);
        if (getSaldo().compareTo(valor) >= 0) {
            setSaldo(getSaldo().subtract(valor));
        } else throw new IllegalArgumentException("Saldo insuficiente.");
    }
}
