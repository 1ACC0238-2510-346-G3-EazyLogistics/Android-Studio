// LoginRequest.kt
package pe.edu.upc.logisticmaster.data.remote.dto

/**
 * Payload para iniciar sesión.
 */
data class LoginRequest(
    val usuario: String,
    val contrasena: String
)
