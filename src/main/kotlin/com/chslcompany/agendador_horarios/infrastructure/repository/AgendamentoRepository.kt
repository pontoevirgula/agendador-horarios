package com.chslcompany.agendador_horarios.infrastructure.repository

import com.chslcompany.agendador_horarios.infrastructure.entity.Agendamento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


interface AgendamentoRepository : JpaRepository<Agendamento, Long> {
    fun findByServicoAndDataHoraAgendamentoBetween(
        servico: String?, dataHoraInicio: LocalDateTime?,
        dataHoraFinal: LocalDateTime?
    ): Agendamento?

    @Transactional
    fun deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento: LocalDateTime, cliente: String)

    fun findByDataHoraAgendamentoBetween(
        dataHoraInicial: LocalDateTime,
        dataHoraFinal: LocalDateTime
    ): List<Agendamento>

    fun findByDataHoraAgendamentoAndCliente(dataHoraAgendamento: LocalDateTime, cliente: String): Agendamento?
}