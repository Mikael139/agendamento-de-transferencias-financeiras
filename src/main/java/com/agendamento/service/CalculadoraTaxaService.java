package com.agendamento.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class CalculadoraTaxaService {

    public BigDecimal calcularTaxa(LocalDate dataAgendamento, LocalDate dataTransferencia, BigDecimal valor) {
        long diasDiferenca = ChronoUnit.DAYS.between(dataAgendamento, dataTransferencia);

        if (diasDiferenca == 0) {
            return valor.multiply(new BigDecimal("0.025")).add(new BigDecimal("3.00"));
        } else if (diasDiferenca >= 1 && diasDiferenca <= 10) {
            return new BigDecimal("12.00");
        } else if (diasDiferenca >= 11 && diasDiferenca <= 20) {
            return valor.multiply(new BigDecimal("0.082"));
        } else if (diasDiferenca >= 21 && diasDiferenca <= 30) {
            return valor.multiply(new BigDecimal("0.069"));
        } else if (diasDiferenca >= 31 && diasDiferenca <= 40) {
            return valor.multiply(new BigDecimal("0.047"));
        } else if (diasDiferenca >= 41 && diasDiferenca <= 50) {
            return valor.multiply(new BigDecimal("0.017"));
        } else {
            // Caso não haja taxa aplicável
            throw new IllegalArgumentException("Não há taxa aplicável para a data de transferência fornecida.");
        }
    }
}
