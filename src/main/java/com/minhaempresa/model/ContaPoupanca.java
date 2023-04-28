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

    public ContaPoupanca(Long id, String tipo, String numeroConta, BigDecimal saldo, LocalDate dataCriacao, Titular titular) {
        super(id, tipo, numeroConta, saldo, dataCriacao, titular);
    }

    @Override
    public void depositar(BigDecimal valor) {
        setSaldo(getSaldo().add(valor));
    }

    @Override
    public void sacar(BigDecimal valor) {
        setSaldo(getSaldo().subtract(valor));
    }
}
