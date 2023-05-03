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
    @Column(unique = true)
    private String numeroConta;
    private BigDecimal saldo = BigDecimal.ZERO;
    private LocalDate dataCriacao;
    private static int ultimaContaGerada = 10000;
    @ManyToOne
    private Cliente titular;

    public Conta(Long id, String tipo, LocalDate dataCriacao, Cliente titular) {
        this.id = id;
        this.tipo = tipo;
        this.numeroConta = String.valueOf(++ultimaContaGerada);
        this.dataCriacao = dataCriacao;
        this.titular = titular;
    }

    public Conta(Long id, String tipo, BigDecimal valor, LocalDate dataCriacao, Cliente titular) {
        this.id = id;
        this.tipo = tipo;
        this.numeroConta = String.valueOf(++ultimaContaGerada);
        this.saldo = valor;
        this.dataCriacao = dataCriacao;
        this.titular = titular;
    }

    public abstract void depositar(BigDecimal valor);
    public abstract void sacar(BigDecimal valor);



}
