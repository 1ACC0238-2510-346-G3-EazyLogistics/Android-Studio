package pe.edu.upc.logisticmaster.presentation.viewmodel.auth

import pe.edu.upc.logisticmaster.domain.model.Usuario

/**
 * Estados de UI para los procesos de autenticación.
 */
sealed class AuthUiState {
    object Idle : AuthUiState()               // sin acción
    object Loading : AuthUiState()            // en curso
    data class Success(val user: Usuario) : AuthUiState()   // login/registro OK
    data class Error(val message: String)   : AuthUiState()  // ocurrió un error
}
