package com.minhaempresa.repositories;

import com.minhaempresa.model.Conta;
import com.minhaempresa.model.Titular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TitularRepository extends JpaRepository<Titular, Long> {
    @Query("SELECT c FROM Conta c WHERE c.titular.id = :id")
    List<Conta> buscarContasPorTitular(@Param("id") Long id);
}
