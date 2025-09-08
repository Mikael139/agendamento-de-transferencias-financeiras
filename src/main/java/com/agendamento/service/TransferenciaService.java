package com.agendamento.service;

import com.agendamento.model.TransferenciaModel;
import com.agendamento.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private CalculadoraTaxaService calculadoraTaxaService;

    public TransferenciaModel agendarTransferencia(TransferenciaModel transferencia) {
        BigDecimal taxa = calculadoraTaxaService.calcularTaxa(transferencia.getDataAgendamento(), transferencia.getDataTransferencia(), transferencia.getValor());
        transferencia.setTaxa(taxa);
        return transferenciaRepository.save(transferencia);
    }

    public List<TransferenciaModel> getExtrato() {
        return transferenciaRepository.findAll();
    }
}
