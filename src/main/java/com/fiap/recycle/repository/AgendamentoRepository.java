package com.fiap.recycle.repository;

import com.fiap.recycle.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
