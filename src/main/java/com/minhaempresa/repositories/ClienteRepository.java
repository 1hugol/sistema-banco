package com.minhaempresa.repositories;

import com.minhaempresa.controllers.response.GetContaSemTitularResponse;
import com.minhaempresa.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT new com.minhaempresa.controllers.response.GetContaSemTitularResponse(c.tipo, c.numeroConta) FROM Conta c WHERE c.titular.id = :id")
    List<GetContaSemTitularResponse> buscarContasPorIdTitular(@Param("id") Long id);

    @Query("SELECT new com.minhaempresa.controllers.response.GetContaSemTitularResponse(c.tipo, c.numeroConta) FROM Conta c WHERE c.titular.cpf = :cpf")
    List<GetContaSemTitularResponse> buscarContasPorCpfTitular(@Param("cpf") String cpf);

    @Query("SELECT t FROM Cliente t WHERE t.cpf = :cpf")
    Cliente findByCpf(@Param("cpf") String cpf);
}
