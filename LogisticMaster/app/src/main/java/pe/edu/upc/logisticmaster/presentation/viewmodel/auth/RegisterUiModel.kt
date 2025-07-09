package pe.edu.upc.logisticmaster.presentation.viewmodel.auth

/**
 * Datos necesarios para registrar un nuevo usuario.
 */
data class RegisterUiModel(
    val nombre: String,
    val apellido: String,
    val usuario: String,
    val email: String,
    val contrasena: String
)
