package com.fiap.recycle.model;

import com.fiap.recycle.dto.Agendamento.Request.AgendamentoRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity(name = "Agendamento")
@Table(name = "agendamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @ManyToOne
    private Endereco endereco;

    private LocalDateTime dataInicio = LocalDateTime.now();
    private LocalDateTime dataFinalizado;

    public Agendamento(AgendamentoRequestDTO agendamentoRequestDTO) {
        this.descricao = agendamentoRequestDTO.descricao();

    }

}
