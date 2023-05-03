package com.minhaempresa.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetClienteComContasResponse {
    private Long titularId;
    private String nome;
    private List<GetContaSemTitularResponse> listaContas;
}
