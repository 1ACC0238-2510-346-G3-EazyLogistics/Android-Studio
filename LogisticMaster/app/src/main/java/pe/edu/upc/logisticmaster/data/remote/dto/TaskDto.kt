package pe.edu.upc.logisticmaster.data.remote.dto

/**
 * DTO para Tarea (Task) expuesto por la API.
 */
data class TaskDto(
    val id: Long?,
    val titulo: String,
    val descripcion: String,
    val workerId: Long?
)
