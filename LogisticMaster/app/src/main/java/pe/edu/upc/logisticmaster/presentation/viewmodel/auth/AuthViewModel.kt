package pe.edu.upc.logisticmaster.presentation.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.logisticmaster.domain.repository.UserRepository
import pe.edu.upc.logisticmaster.domain.model.User

class AuthViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val authState: StateFlow<AuthUiState> = _authState

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    fun register(username: String, nombre: String, apellido: String, email: String, password: String) {
        if (username.isBlank() || nombre.isBlank() || apellido.isBlank() || email.isBlank() || password.isBlank()) {
            _authState.value = AuthUiState.Error("Todos los campos son requeridos")
            return
        }

        viewModelScope.launch {
            _authState.value = AuthUiState.Loading
            try {
                val user = User(
                    id = null,
                    usuario = username,
                    email = email,
                    nombre = nombre,
                    apellido = apellido,
                    password = password
                )
                val registeredUser = userRepository.register(user)
                _currentUser.value = registeredUser
                _authState.value = AuthUiState.Success("Registro exitoso")
            } catch (e: Exception) {
                _authState.value = AuthUiState.Error(e.message ?: "Error en el registro")
            }
        }
    }

    fun login(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            _authState.value = AuthUiState.Error("Usuario y contraseña son requeridos")
            return
        }

        viewModelScope.launch {
            _authState.value = AuthUiState.Loading
            try {
                val user = userRepository.login(username)
                _currentUser.value = user
                _authState.value = AuthUiState.Success("Inicio de sesión exitoso")
            } catch (e: Exception) {
                _authState.value = AuthUiState.Error("Credenciales inválidas")
            }
        }
    }

    fun logout() {
        _currentUser.value = null
        _authState.value = AuthUiState.Idle
    }

    fun clearError() {
        _authState.value = AuthUiState.Idle
    }
}
