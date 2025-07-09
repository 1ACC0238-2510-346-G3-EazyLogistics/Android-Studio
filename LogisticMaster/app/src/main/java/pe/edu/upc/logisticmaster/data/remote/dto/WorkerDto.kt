package pe.edu.upc.logisticmaster.data.remote.dto

data class WorkerDto(
    val id: Int,
    val nombre: String,
    val email: String,
    val telefono: String,
    val puesto: String,
    val area: String
)
