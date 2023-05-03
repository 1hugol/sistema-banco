package com.minhaempresa.config;

import com.minhaempresa.model.Conta;
import com.minhaempresa.model.ContaCorrente;
import com.minhaempresa.model.ContaPoupanca;
import com.minhaempresa.model.Cliente;
import com.minhaempresa.repositories.ContaRepository;
import com.minhaempresa.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public void run(String... args) throws Exception {

        Cliente t1 = new Cliente(null, "Hugo", "00011122233");

        clienteRepository.save(t1);

        Conta c1 = new ContaCorrente(null, "CC", LocalDate.of(2022, 02, 10), t1);
        Conta c2 = new ContaPoupanca(null, "CP", new BigDecimal("100"), LocalDate.of(2022, 02, 10), t1);
        Conta c3 = new ContaCorrente(null, "CC", LocalDate.of(2022, 02, 10), t1);

        contaRepository.saveAll(Arrays.asList(c1, c2, c3));

        t1.getContas().addAll(Arrays.asList(c1, c2, c3));

        clienteRepository.save(t1);
    }
}
