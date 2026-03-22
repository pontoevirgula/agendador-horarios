package com.chslcompany.agendador_horarios.controller

import com.chslcompany.agendador_horarios.infrastructure.entity.Agendamento
import com.chslcompany.agendador_horarios.service.AgendamentoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalDateTime

@RestController
@RequestMapping("/agendamentos")
class AgendamentoController(
    private val agendamentoService: AgendamentoService
) {
    @PostMapping
    fun salvarAgendamento(@RequestBody agendamento: Agendamento): ResponseEntity<Agendamento> =
        ResponseEntity.accepted().body(agendamentoService.salvarAgendamento(agendamento))

    @DeleteMapping
    fun deletarAgendamento(
        @RequestParam cliente: String,
        @RequestParam dataHoraAgendamento: LocalDateTime
    ): ResponseEntity<Void> {
        agendamentoService.deletarAgendamento(dataHoraAgendamento, cliente)
        return ResponseEntity.noContent().build()
    }

    @GetMapping
    fun buscarAgendamentosDia(@RequestParam data: LocalDate): ResponseEntity<List<Agendamento>> =
        ResponseEntity.ok().body(agendamentoService.buscarAgendamentosDia(data))

    @PutMapping
    fun alterarAgendamentos(
        @RequestBody agendamento: Agendamento,
        @RequestParam cliente: String,
        @RequestParam dataHoraAgendamento: LocalDateTime
    ): ResponseEntity<Agendamento> =
        ResponseEntity.accepted().body(agendamentoService.alterarAgendamento(agendamento, cliente, dataHoraAgendamento))
}