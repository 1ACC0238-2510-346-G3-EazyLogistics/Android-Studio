package pe.edu.upc.logisticmaster.presentation.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.logisticmaster.data.remote.api.AuthApiService
import pe.edu.upc.logisticmaster.data.remote.dto.LoginRequest
import pe.edu.upc.logisticmaster.data.remote.dto.RegisterRequest
import pe.edu.upc.logisticmaster.domain.model.Usuario

class AuthViewModel(
    private val api: AuthApiService
) : ViewModel() {

    private val _state = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val state: StateFlow<AuthUiState> = _state

    fun login(usuario: String, contrasena: String) {
        viewModelScope.launch {
            _state.value = AuthUiState.Loading
            try {
                val dto = api.getByUsuario(usuario)
                if (dto.contrasena == contrasena) {
                    val user = Usuario(
                        id         = dto.id,
                        nombre     = dto.nombre,
                        apellido   = dto.apellido,
                        usuario    = dto.usuario,
                        email      = dto.email,
                        contrasena = dto.contrasena
                    )
                    _state.value = AuthUiState.Success(user)
                } else {
                    _state.value = AuthUiState.Error("Contraseña incorrecta")
                }
            } catch (e: Exception) {
                _state.value = AuthUiState.Error("Usuario no encontrado")
            }
        }
    }

    fun register(model: RegisterUiModel) {
        viewModelScope.launch {
            _state.value = AuthUiState.Loading
            try {
                // Llamamos al endpoint de registro y recibimos el DTO
                val dto = api.register(
                    RegisterRequest(
                        model.nombre,
                        model.apellido,
                        model.usuario,
                        model.email,
                        model.contrasena
                    )
                )
                // Mapear DTO a dominio
                val user = Usuario(
                    id         = dto.id,
                    nombre     = dto.nombre,
                    apellido   = dto.apellido,
                    usuario    = dto.usuario,
                    email      = dto.email,
                    contrasena = dto.contrasena
                )
                // Emitir Success con el usuario recién creado
                _state.value = AuthUiState.Success(user)
            } catch (e: Exception) {
                _state.value = AuthUiState.Error(e.message.orEmpty())
            }
        }
    }

    fun logout() {
        _state.value = AuthUiState.Idle
    }
}
