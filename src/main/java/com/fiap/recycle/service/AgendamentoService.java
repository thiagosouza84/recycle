package com.fiap.recycle.service;

import com.fiap.recycle.dto.Agendamento.Request.AgendamentoRequestDTO;
import com.fiap.recycle.dto.Agendamento.Response.AgendamentoResponseDTO;
import com.fiap.recycle.model.Agendamento;
import com.fiap.recycle.model.Endereco;
import com.fiap.recycle.repository.AgendamentoRepository;
import com.fiap.recycle.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    AgendamentoRepository agendamentoRepository;
    @Autowired
    EnderecoRepository enderecoRepository;

    public List<AgendamentoResponseDTO> listarAgendamentos() {
        return this.agendamentoRepository.findAll().stream().map(AgendamentoResponseDTO::new).toList();
    }

    public AgendamentoResponseDTO criarAgendamento(AgendamentoRequestDTO agendamentoRequestDTO) {
        Endereco endereco = new Endereco(agendamentoRequestDTO.endereco());
        enderecoRepository.save(endereco);
        Agendamento newAgendamento = new Agendamento(agendamentoRequestDTO);
        newAgendamento.setEndereco(endereco);
        agendamentoRepository.save(newAgendamento);
        return new AgendamentoResponseDTO(newAgendamento);
    }

    public AgendamentoResponseDTO atualizarAgendamento(Long id, AgendamentoRequestDTO agendamentoRequestDTO) {
        Optional<Agendamento> agendamentoOptional = agendamentoRepository.findById(id);

        if (agendamentoOptional.isPresent()) {
            Agendamento agendamentoUpdate = agendamentoOptional.get();

            Endereco enderecoUpdate;
            enderecoUpdate = enderecoRepository.getReferenceById(agendamentoUpdate.getEndereco().getId());

            enderecoUpdate.setCep(agendamentoRequestDTO.endereco().cep());
            enderecoUpdate.setRua(agendamentoRequestDTO.endereco().rua());
            enderecoUpdate.setEstado(agendamentoRequestDTO.endereco().estado());
            enderecoUpdate.setCidade(agendamentoRequestDTO.endereco().cidade());
            enderecoRepository.save(enderecoUpdate);

            agendamentoUpdate.setDescricao(agendamentoRequestDTO.descricao());
            agendamentoUpdate.setEndereco(enderecoUpdate);
            agendamentoRepository.save(agendamentoUpdate);

            return new AgendamentoResponseDTO(agendamentoUpdate);
        } else throw new RuntimeException("Agendamento não encontrado. verificar id");
    }

    public void deletarAgendamento(Long id) {
        if (agendamentoRepository.existsById(id)) {
            agendamentoRepository.deleteById(id);
        } else throw new RuntimeException("Agendamento não encontrado com id: " + id);
    }
}
