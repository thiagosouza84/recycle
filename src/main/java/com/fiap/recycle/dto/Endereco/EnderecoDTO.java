package com.fiap.recycle.dto.Endereco;

import com.fiap.recycle.dto.Agendamento.Request.AgendamentoRequestDTO;
import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
        @NotBlank(message = "Necessário nome da rua")
        String rua,
        @NotBlank(message = "Necessário nome da cidade")
        String cidade,
        @NotBlank(message = "Necessário nome do estado")
        String estado,
        @NotBlank(message = "Necessário o cep")
        String cep) {


}
