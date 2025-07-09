package pe.edu.upc.logisticmaster.presentation.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.logisticmaster.data.remote.api.AuthApiService
import pe.edu.upc.logisticmaster.data.remote.dto.LoginRequest
import pe.edu.upc.logisticmaster.data.remote.dto.RegisterRequest
import pe.edu.upc.logisticmaster.data.remote.dto.UsuarioDto

class AuthViewModel(
    private val api: AuthApiService
) : ViewModel() {

    private val _state = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val state: StateFlow<AuthUiState> = _state

    fun login(model: LoginUiModel) {
        viewModelScope.launch {
            _state.value = AuthUiState.Loading
            try {
                val user: UsuarioDto = api.login(
                    LoginRequest(
                        usuario    = model.usuario,
                        contrasena = model.contrasena
                    )
                )
                _state.value = AuthUiState.Success("Login exitoso: ${user.usuario}")
            } catch (e: Exception) {
                _state.value = AuthUiState.Error(e.message.orEmpty())
            }
        }
    }

    fun register(model: RegisterUiModel) {
        viewModelScope.launch {
            _state.value = AuthUiState.Loading
            try {
                val user: UsuarioDto = api.register(
                    RegisterRequest(
                        nombre     = model.nombre,
                        apellido   = model.apellido,
                        usuario    = model.usuario,
                        email      = model.email,
                        contrasena = model.contrasena
                    )
                )
                _state.value = AuthUiState.Success("Registro exitoso: ${user.usuario}")
            } catch (e: Exception) {
                _state.value = AuthUiState.Error(e.message.orEmpty())
            }
        }
    }
}
