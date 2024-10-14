package com.fiap.recycle.dto.User;

import com.fiap.recycle.model.Usuario;

public record UsuarioResponseDTO(String name, String email) {

    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getName(), usuario.getEmail());
    }

}

