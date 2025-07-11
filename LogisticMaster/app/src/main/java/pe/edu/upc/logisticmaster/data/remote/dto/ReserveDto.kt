package pe.edu.upc.logisticmaster.data.remote.dto

data class ReserveDto(
    val id: Long?,
    val fechaEntrada: String,
    val fechaSalida: String,
    val estado: String,
    val numeroHabitacion: Int,
    val precio: Double,
    val userId: Long,
    val hotelId: Long
)
