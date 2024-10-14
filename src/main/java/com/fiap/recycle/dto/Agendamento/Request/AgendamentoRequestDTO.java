package com.fiap.recycle.dto.Agendamento.Request;

import com.fiap.recycle.dto.Endereco.EnderecoDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record AgendamentoRequestDTO(
        @NotBlank(message = "Deve conter uma descrição")
        String descricao,
        @NotNull
        EnderecoDTO endereco) {
}
