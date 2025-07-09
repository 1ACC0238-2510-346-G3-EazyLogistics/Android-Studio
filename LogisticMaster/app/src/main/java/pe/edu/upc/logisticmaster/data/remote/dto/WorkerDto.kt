// data/remote/dto/WorkerDto.kt
package pe.edu.upc.logisticmaster.data.remote.dto

data class WorkerDto(
    val id: Long,
    val nombre: String,
    val apellido: String,
    val email: String,
    val telefono: String,
    val puesto: String,
    val area: String
)
