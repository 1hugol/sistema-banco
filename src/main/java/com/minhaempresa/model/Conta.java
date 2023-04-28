package com.minhaempresa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 2, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("CO")
@Table(name = "TB_CONTA")
public abstract class Conta{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(insertable = false, updatable = false)
    private String tipo;
    private String numeroConta;
    private BigDecimal saldo;
    private LocalDate dataCriacao;
    @ManyToOne
    private Titular titular;

    public abstract void depositar(BigDecimal valor);
    public abstract void sacar(BigDecimal valor);
}
