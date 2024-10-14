package com.fiap.recycle.dto.User.Request;

import com.fiap.recycle.enums.Roles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioCreateRequestDTO(
        @NotBlank(message = "nome necessário")
        String name,
        @NotBlank(message = "email necessário")
        String email,
        @NotBlank(message = "senha necessária")
        String password,
        @NotNull
        Roles role) {
}
