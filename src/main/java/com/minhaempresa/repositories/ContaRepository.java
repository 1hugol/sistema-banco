package com.minhaempresa.repositories;

import com.minhaempresa.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository<T extends Conta> extends JpaRepository<T, Long> {
}
