package com.minhaempresa.model;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@DiscriminatorValue("CC")
public class ContaCorrente extends Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    public ContaCorrente(Long id, String tipo, String numeroConta, Double saldo, LocalDate dataCriacao, Titular titular) {
        super(id, tipo, numeroConta, saldo, dataCriacao, titular);
    }
}