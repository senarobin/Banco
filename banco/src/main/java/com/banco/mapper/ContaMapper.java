package com.banco.mapper;

import com.banco.dto.ContaDto;
import com.banco.entity.Conta;

public class ContaMapper {

    public static Conta MapConta(ContaDto contaDto) {
        Conta conta = new Conta(contaDto.getId(), contaDto.getNome(), contaDto.getSaldo());
        return conta;
    }

    public static ContaDto MapConta(Conta conta) {

        ContaDto contaDto = new ContaDto(conta.getId(), conta.getNome(), conta.getSaldo());
        return contaDto;
    }
}
