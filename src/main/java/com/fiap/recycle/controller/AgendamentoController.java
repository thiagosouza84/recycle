package com.fiap.recycle.controller;

import com.fiap.recycle.dto.Agendamento.Request.AgendamentoRequestDTO;
import com.fiap.recycle.dto.Agendamento.Response.AgendamentoResponseDTO;
import com.fiap.recycle.model.Agendamento;
import com.fiap.recycle.service.AgendamentoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("agendamento")
public class AgendamentoController {

    @Autowired
    AgendamentoService agendamentoService;


    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> listarColetas() {
        var listAgendamentos = agendamentoService.listarAgendamentos();
        return ResponseEntity.status(HttpStatus.OK).body(listAgendamentos);
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> criarColeta(@RequestBody @Valid AgendamentoRequestDTO agendamentoRequestDTO) {
        var newAgendamento = agendamentoService.criarAgendamento(agendamentoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAgendamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> atualizarColeta(@PathVariable Long id, @RequestBody @Valid AgendamentoRequestDTO agendamentoRequestDTO) {
        var agendamentoAtualizado = agendamentoService.atualizarAgendamento(id, agendamentoRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarColeta(@PathVariable Long id) {
        agendamentoService.deletarAgendamento(id);
        return ResponseEntity.noContent().build();
    }


}
