package com.agendamento.controller;

import com.agendamento.model.TransferenciaModel;
import com.agendamento.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping("/agendar")
    public ResponseEntity<TransferenciaModel> agendarTransferencia(@RequestBody TransferenciaModel transferencia) {
        try {
            TransferenciaModel novaTransferencia = transferenciaService.agendarTransferencia(transferencia);
            return ResponseEntity.ok(novaTransferencia);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/extrato")
    public ResponseEntity<List<TransferenciaModel>> getExtrato() {
        List<TransferenciaModel> extrato = transferenciaService.getExtrato();
        return ResponseEntity.ok(extrato);
    }
}
