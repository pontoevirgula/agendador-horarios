package com.chslcompany.agendador_horarios.service

import com.chslcompany.agendador_horarios.infrastructure.entity.Agendamento
import com.chslcompany.agendador_horarios.infrastructure.repository.AgendamentoRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class AgendamentoService(
    private val agendamentoRepository: AgendamentoRepository
) {
    fun salvarAgendamento(agendamento: Agendamento): Agendamento {
        val horaAgendamento = agendamento.dataHoraAgendamento
        val horaFim = agendamento.dataHoraAgendamento?.plusMinutes(1)
        val agendados = agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(
            agendamento.servico, horaAgendamento, horaFim
        )
        if (agendados != null) {
            throw RuntimeException("Horário já está preenchido")
        }
        return agendamentoRepository.save(agendamento)
    }

    fun deletarAgendamento(dataHoraAgendamento: LocalDateTime, cliente: String) {
        agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente)
    }

    fun buscarAgendamentosDia(data: LocalDate): List<Agendamento> {
        val primeiraHoraDia = data.atStartOfDay()
        val horaFinalDia = data.atTime(23, 59, 59)
        return agendamentoRepository.findByDataHoraAgendamentoBetween(primeiraHoraDia, horaFinalDia)
    }

    fun alterarAgendamento(agendamento: Agendamento, cliente: String, dataHoraAgendamento: LocalDateTime): Agendamento {
        val agenda = agendamentoRepository.findByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente)
            ?: throw RuntimeException("Horário não está preenchido")

        agendamento.id = agenda.id
        return agendamentoRepository.save(agendamento)
    }
}