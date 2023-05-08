package com.minhaempresa;

import com.minhaempresa.model.Cliente;
import com.minhaempresa.model.ContaCorrente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContaCorrenteTest {
    private ContaCorrente contaCorrente;

    @BeforeEach
    public void setUp() {
        Cliente cliente = new Cliente(1L, "Fulano", "12345678901");
        contaCorrente = new ContaCorrente(1L, BigDecimal.valueOf(1000.00), LocalDate.now(), cliente);
    }

    @Test
    public void testDepositar() {
        contaCorrente.depositar(BigDecimal.valueOf(500.00));
        assertEquals(BigDecimal.valueOf(1500.00), contaCorrente.getSaldo());
    }

    @Test
    public void testSacar() {
        contaCorrente.sacar(BigDecimal.valueOf(500.00));
        assertEquals(BigDecimal.valueOf(497.00), contaCorrente.getSaldo());
    }

    @Test
    public void testSacarValorMaiorQueSaldo() {
        assertThrows(IllegalArgumentException.class,  () -> contaCorrente.sacar(BigDecimal.valueOf(1500.00)));
    }
}
