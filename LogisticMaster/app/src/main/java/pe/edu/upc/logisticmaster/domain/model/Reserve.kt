// Reserve.kt
package pe.edu.upc.logisticmaster.domain.model

/**
 * Modelo de dominio para una reserva.
 */
data class Reserve(
    val id: Long?,
    val fechaEntrada: String,
    val fechaSalida: String,
    val estado: String,
    val numeroHabitacion: Int,
    val precio: Double,
    val userId: Long,
    val hotelId: Long
    // La fecha de creación la genera el backend y no se maneja en el dominio.
)
