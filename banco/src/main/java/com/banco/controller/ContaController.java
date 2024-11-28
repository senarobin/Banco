package com.banco.controller;

import com.banco.dto.ContaDto;
import com.banco.service.ContaServico;
import com.banco.service.impl.ContaServicoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("contas")
public class ContaController {

    private ContaServicoImpl contaServico;

    @Autowired
    public ContaController(ContaServico contaServico) {
        this.contaServico = (ContaServicoImpl) contaServico;
    }

    @PostMapping
    public ResponseEntity<ContaDto> adicionarConta(@RequestBody ContaDto contaDto) {
        return new ResponseEntity<>(contaServico.criarConta(contaDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDto> getContaPorId(@PathVariable Long id) {

        ContaDto contaDto = contaServico.getContaPorId(id);

        return ResponseEntity.ok(contaDto);
    }

    @PutMapping("/{id}/deposito")
    public ResponseEntity<ContaDto> deposito(@PathVariable Long id, @RequestBody Map<String, Double> request) {

        Double quantia = request.get("quantia");
        ContaDto contaDto = contaServico.deposito(id, quantia);

        return ResponseEntity.ok(contaDto);
    }

    @PutMapping("/{id}/saque")
    public ResponseEntity<ContaDto> saque(@PathVariable Long id, @RequestBody Map<String, Double> request) {

        Double quantia = request.get("quantia");
        ContaDto contaDto = contaServico.saque(id, quantia);

        return ResponseEntity.ok(contaDto);
    }

    @GetMapping
    public ResponseEntity<List<ContaDto>> ListarTodasContas () {

        List<ContaDto> contaDto = contaServico.ListarTodasContas();

        return ResponseEntity.ok(contaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarConta(@PathVariable Long id) {

        contaServico.deletarConta(id);

        return ResponseEntity.ok("Conta deletada com sucesso");
    }
}
