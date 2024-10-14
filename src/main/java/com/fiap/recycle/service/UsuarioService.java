package com.fiap.recycle.service;


import com.fiap.recycle.dto.User.UsuarioResponseDTO;
import com.fiap.recycle.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDTO> listarUsuarios() {

        return usuarioRepository.findAll().stream().map(UsuarioResponseDTO::new).toList();
    }

}
