package com.fiap.recycle.model;

import com.fiap.recycle.dto.Agendamento.Request.AgendamentoRequestDTO;
import com.fiap.recycle.dto.Endereco.EnderecoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "Endereco")
@Table(name = "endereco")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rua;
    private String cidade;
    private String estado;
    private String cep;


    public Endereco(EnderecoDTO enderecoDTO) {
        this.rua = enderecoDTO.rua();
        this.cidade = enderecoDTO.cidade();
        this.estado = enderecoDTO.estado();
        this.cep = enderecoDTO.cep();

    }

}
