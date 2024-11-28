package com.banco.service.impl;

import com.banco.dto.ContaDto;
import com.banco.entity.Conta;
import com.banco.mapper.ContaMapper;
import com.banco.repository.ContaRepositorio;
import com.banco.service.ContaServico;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaServicoImpl implements ContaServico {

    private ContaRepositorio contaRepositorio;

    public ContaServicoImpl(ContaRepositorio contaRepositorio) {
        super();
        this.contaRepositorio = contaRepositorio;
    }

    @Override
    public ContaDto criarConta(ContaDto contaDto) {

        Conta conta = ContaMapper.MapConta(contaDto);
        Conta salvarConta = contaRepositorio.save(conta);
        return ContaMapper.MapConta(salvarConta);
    }

    @Override
    public ContaDto getContaPorId(Long id) {

        Conta conta = contaRepositorio.findById(id).
                orElseThrow(() -> new RuntimeException("Conta não existe"));

        return ContaMapper.MapConta(conta);
    }

    @Override
    public ContaDto deposito(Long id, double quantia) {

        Conta conta = contaRepositorio.findById(id).
                orElseThrow(() -> new RuntimeException("Conta inexistente"));

        double saldoTotal = conta.getSaldo() + quantia;
        conta.setSaldo(saldoTotal);

        Conta salvarConta = contaRepositorio.save(conta);

        return ContaMapper.MapConta(salvarConta);
    }

    @Override
    public ContaDto saque(Long id, double quantia) {

        Conta conta = contaRepositorio.findById(id).
                orElseThrow(() -> new RuntimeException("Conta inexistente"));

        if(conta.getSaldo() < quantia) {
            throw new RuntimeException("Saldo insuficiente");
        }

        double saldoTotal = conta.getSaldo() - quantia;
        conta.setSaldo(saldoTotal);

        Conta salvarConta = contaRepositorio.save(conta);

        return ContaMapper.MapConta(salvarConta);
    }

    @Override
    public List<ContaDto> ListarTodasContas() {

        return contaRepositorio.findAll().stream().map((conta) -> ContaMapper.MapConta(conta))
                .collect(Collectors.toList());
    }

    @Override
    public void deletarConta(Long id) {

        Conta conta = contaRepositorio.findById(id).
                orElseThrow(() -> new RuntimeException("Conta inexistente"));

        contaRepositorio.delete(conta);
    }
}
