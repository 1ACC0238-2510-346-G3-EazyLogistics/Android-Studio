// Reserve.kt
package pe.edu.upc.logisticmaster.domain.model

/**
 * Modelo de dominio para una reserva.
 */
data class Reserve(
    val id: Long?,
    val nombreHuespedes: String,
    val habitacion: String,
    val horaIngreso: String,
    val horaSalida: String
    // La fecha de creación la genera el backend y no se maneja en el dominio.
)
