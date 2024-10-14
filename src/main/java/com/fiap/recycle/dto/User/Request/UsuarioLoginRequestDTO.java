package com.fiap.recycle.dto.User.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioLoginRequestDTO(
        @NotBlank(message = "Email é necessário")
        String Email,
        @NotBlank(message = "A senha é necessária")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 a 20 caracteres.")
        String Password) {
}
