package com.minhaempresa.controllers.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PostContaRequest {

    private String tipo;
    private BigDecimal saldo = BigDecimal.ZERO;
    private LocalDate dataCriacao;

    private Long titular_id;

    public PostContaRequest(String tipo, BigDecimal valor, LocalDate dataCriacao, Long titular_id) {
        this.tipo = tipo;
        this.saldo = valor;
        this.dataCriacao = dataCriacao;
        this.titular_id = titular_id;
    }

    public PostContaRequest(String tipo, LocalDate dataCriacao, Long titular_id) {
        this.tipo = tipo;
        this.dataCriacao = dataCriacao;
        this.titular_id = titular_id;
    }
}
