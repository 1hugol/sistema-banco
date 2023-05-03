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

    public ContaPoupanca(Long id, String tipo, BigDecimal valor, LocalDate dataCriacao, Cliente cliente) {
        super(id, tipo, dataCriacao, cliente);
        if (valor.compareTo(new BigDecimal("50")) < 0)
            throw new IllegalArgumentException("Valor mínimo pra abertura de Conta Poupança é R$ 50");
        else depositar(valor);
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
