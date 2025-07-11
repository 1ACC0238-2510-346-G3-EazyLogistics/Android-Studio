// Task.kt
package pe.edu.upc.logisticmaster.domain.model

/**
 * Modelo de dominio para una tarea asociada a un trabajador.
 */
data class Task(
    val id: Long?,
    val titulo: String,
    val descripcion: String,
    val workerId: Long?
)
