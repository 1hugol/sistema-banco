package com.minhaempresa.repositories;

import com.minhaempresa.model.Conta;
import com.minhaempresa.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Conta c WHERE c.titular.id = :id")
    List<Conta> buscarContasPorTitular(@Param("id") Long id);
}
