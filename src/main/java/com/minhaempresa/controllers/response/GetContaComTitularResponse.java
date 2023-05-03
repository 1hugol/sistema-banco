package com.minhaempresa.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetContaComTitularResponse {
    private String tipoConta;
    private String numeroConta;

    private String titularConta;
}
