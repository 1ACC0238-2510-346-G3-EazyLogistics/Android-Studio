// Usuario.kt
package pe.edu.upc.logisticmaster.domain.model

/**
 * Modelo de dominio para un usuario autenticado.
 */
data class Usuario(
    val id: Long?,
    val nombre: String,
    val apellido: String,
    val usuario: String,
    val email: String,
    val contrasena: String
)
