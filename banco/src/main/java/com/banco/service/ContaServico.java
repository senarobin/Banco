package com.banco.service;

import com.banco.dto.ContaDto;

import java.util.List;

public interface ContaServico {

    ContaDto criarConta(ContaDto conta);

    ContaDto getContaPorId(Long id);

    ContaDto deposito(Long id, double quantia);

    ContaDto saque(Long id, double quantia);

    List<ContaDto> ListarTodasContas();

    void deletarConta(Long id);
}
