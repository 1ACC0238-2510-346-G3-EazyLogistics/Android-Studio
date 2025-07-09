package pe.edu.upc.logisticmaster.presentation.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.logisticmaster.data.remote.api.AuthApiService
import pe.edu.upc.logisticmaster.data.remote.dto.LoginRequest
import pe.edu.upc.logisticmaster.data.remote.dto.RegisterRequest

class AuthViewModel(
    private val api: AuthApiService
) : ViewModel() {

    private val _state = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val state: StateFlow<AuthUiState> = _state

    fun login(model: LoginUiModel) {
        viewModelScope.launch {
            _state.value = AuthUiState.Loading
            try {
                api.login(LoginRequest(model.usuario, model.contrasena))
                _state.value = AuthUiState.Success("Login exitoso")
            } catch (e: Exception) {
                _state.value = AuthUiState.Error(e.message.orEmpty())
            }
        }
    }

    fun register(model: RegisterUiModel) {
        viewModelScope.launch {
            _state.value = AuthUiState.Loading
            try {
                api.register(RegisterRequest(
                    model.nombre,
                    model.apellido,
                    model.usuario,
                    model.email,
                    model.contrasena
                ))
                _state.value = AuthUiState.Success("Registro exitoso")
            } catch (e: Exception) {
                _state.value = AuthUiState.Error(e.message.orEmpty())
            }
        }
    }

    fun logout() {
        _state.value = AuthUiState.Idle
    }
}
