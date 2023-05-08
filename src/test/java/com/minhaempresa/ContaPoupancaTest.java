package com.minhaempresa;

import com.minhaempresa.model.Cliente;
import com.minhaempresa.model.ContaPoupanca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContaPoupancaTest {
    private ContaPoupanca contaPoupanca;

    @BeforeEach
    public void setUp() {
        Cliente cliente = new Cliente(1L, "Fulano", "12345678901");
        contaPoupanca = new ContaPoupanca(1L, BigDecimal.valueOf(1000.00), LocalDate.now(), cliente);
    }

    @Test
    public void testDepositar() {
        contaPoupanca.depositar(BigDecimal.valueOf(500.00));
        assertEquals(BigDecimal.valueOf(1500.00), contaPoupanca.getSaldo());
    }

    @Test
    public void testSacar() {
        contaPoupanca.sacar(BigDecimal.valueOf(500.00));
        assertEquals(BigDecimal.valueOf(500.00), contaPoupanca.getSaldo());
    }

    @Test
    public void testSacarValorMaiorQueSaldo() {
        assertThrows(IllegalArgumentException.class,  () -> contaPoupanca.sacar(BigDecimal.valueOf(1500.00)));
    }

    @Test
    public void testAberturaContaComValorAbaixoDoMinimo() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new ContaPoupanca(2L, new BigDecimal("10"), LocalDate.now(), new Cliente(1L, "Fulano", "12345678901"));
        });
        assertEquals("Valor mínimo pra abertura de Conta Poupança é R$ 50", exception.getMessage());
    }
}
