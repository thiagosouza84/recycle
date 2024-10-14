package com.fiap.recycle.dto.Agendamento.Response;

import com.fiap.recycle.model.Agendamento;
import com.fiap.recycle.model.Endereco;

public record AgendamentoResponseDTO(Long id, String descricao, Endereco endereco) {

    public AgendamentoResponseDTO(Agendamento agendamento) {
        this(agendamento.getId(), agendamento.getDescricao(), agendamento.getEndereco());

    }
}