package com.chslcompany.agendador_horarios.infrastructure.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "agendamento")
class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var servico: String? = null
    var profissional: String? = null

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    var dataHoraAgendamento: LocalDateTime? = null
    var cliente: String? = null
    var telefoneCliente: String? = null

    @JsonIgnore
    @get:JsonProperty("dataInsercao")
    var dataInsercao: LocalDateTime? = null

    @PrePersist
    private fun prePersist() {
        dataInsercao = LocalDateTime.now()
    }
}