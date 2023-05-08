package com.minhaempresa.model;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@DiscriminatorValue("CP")
public class ContaPoupanca extends Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    public ContaPoupanca(Long id, BigDecimal valor, LocalDate dataCriacao, Cliente cliente) {
        super(id, "CP", valor, dataCriacao, cliente);
    }

    @Override
    public void depositar(BigDecimal valor) {
        setSaldo(getSaldo().add(valor));
    }

    @Override
    public void sacar(BigDecimal valor) {
        if (getSaldo().compareTo(valor) >= 0)
            setSaldo(getSaldo().subtract(valor));
        else
            throw new IllegalArgumentException("Saldo insuficiente.");
    }
}
