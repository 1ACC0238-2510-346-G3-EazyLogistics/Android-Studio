// Worker.kt
package pe.edu.upc.logisticmaster.domain.model

/**
 * Modelo de dominio para un trabajador.
 */
data class Worker(
    val id: Long?,
    val nombre: String,
    val apellido: String,
    val puesto: String,
    val area: String,
    val email: String,
    val telefono: String
)
