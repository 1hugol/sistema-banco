package com.minhaempresa.config;

import com.minhaempresa.model.Conta;
import com.minhaempresa.model.ContaCorrente;
import com.minhaempresa.model.ContaPoupanca;
import com.minhaempresa.model.Titular;
import com.minhaempresa.repositories.ContaRepository;
import com.minhaempresa.repositories.TitularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private TitularRepository titularRepository;

    @Autowired
    private ContaRepository<Conta> contaRepository;

    @Override
    public void run(String... args) throws Exception {

        Titular t1 = new Titular(null, "Hugo", "00011122233");

        titularRepository.save(t1);

        Conta c1 = new ContaCorrente(null, "CC", "0001", new BigDecimal("11.55"), LocalDate.of(2022, 02,10), t1);
        Conta c2 = new ContaPoupanca(null, "CP", "0002", new BigDecimal("10"), LocalDate.of(2022, 02,10), t1);
        Conta c3 = new ContaCorrente(null, "CC", "0003", new BigDecimal("0"), LocalDate.of(2022, 02,10), t1);

        contaRepository.saveAll(Arrays.asList(c1, c2, c3));

        t1.getContas().addAll(Arrays.asList(c1, c2, c3));

        c1.sacar(new BigDecimal("10"));

        titularRepository.save(t1);
    }
}
