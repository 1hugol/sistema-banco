package com.minhaempresa.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetContaComSaldoResponse {
    private String titularConta;
    private String tipoConta;
    private String numeroConta;
    private BigDecimal saldo;
}
