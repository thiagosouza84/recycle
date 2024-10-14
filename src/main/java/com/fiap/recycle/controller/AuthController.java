package com.fiap.recycle.controller;

import com.fiap.recycle.Security.TokenService;
import com.fiap.recycle.dto.TokenDTO;
import com.fiap.recycle.dto.User.Request.UsuarioCreateRequestDTO;
import com.fiap.recycle.dto.User.Request.UsuarioLoginRequestDTO;
import com.fiap.recycle.dto.User.UsuarioResponseDTO;
import com.fiap.recycle.model.Usuario;
import com.fiap.recycle.repository.UsuarioRepository;
import com.fiap.recycle.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodosUsuarios() {
        List<UsuarioResponseDTO> listarUsuarios = usuarioService.listarUsuarios();

        return ResponseEntity.status(HttpStatus.OK).body(listarUsuarios);
    }


    @PostMapping("/register")

    public UsuarioResponseDTO register(@RequestBody @Valid UsuarioCreateRequestDTO usuarioCreateRequestDTO) {
        var senhaCriptgrafada = new BCryptPasswordEncoder().encode(usuarioCreateRequestDTO.password());
        var newUser = new Usuario(usuarioCreateRequestDTO);
        newUser.setPassword(senhaCriptgrafada);
        usuarioRepository.save(newUser);
        return new UsuarioResponseDTO(newUser);


    }


    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid UsuarioLoginRequestDTO usuarioLoginRequestDTO) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(usuarioLoginRequestDTO.Email(), usuarioLoginRequestDTO.Password());

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());


        return ResponseEntity.ok(new TokenDTO(token));
    }


}


