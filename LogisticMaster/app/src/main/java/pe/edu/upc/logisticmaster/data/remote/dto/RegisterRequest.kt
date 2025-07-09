// RegisterRequest.kt
package pe.edu.upc.logisticmaster.data.remote.dto

/**
 * Payload para registrar un nuevo usuario.
 */
data class RegisterRequest(
    val nombre: String,
    val apellido: String,
    val usuario: String,
    val email: String,
    val contrasena: String
)
