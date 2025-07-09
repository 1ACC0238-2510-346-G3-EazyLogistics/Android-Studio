package pe.edu.upc.logisticmaster.data.remote.dto

import java.time.LocalDateTime

data class ReserveDto(
    val id: Long?,
    val nombreHuespedes: String,
    val habitacion: String,
    val horaIngreso: String,
    val horaSalida: String,
    val fechaCreacion: LocalDateTime?
)
