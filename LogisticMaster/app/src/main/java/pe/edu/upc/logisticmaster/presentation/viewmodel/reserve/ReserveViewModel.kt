package pe.edu.upc.logisticmaster.presentation.viewmodel.reserve

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.logisticmaster.domain.model.Reserve
import pe.edu.upc.logisticmaster.data.repository.ReserveRepository

/**
 * ViewModel para CRUD de reservas.
 */
class ReserveViewModel(
    private val repo: ReserveRepository
) : ViewModel() {

    // Estado general de la UI (lista, loading, error, success)
    private val _uiState = MutableStateFlow<ReserveUiState>(ReserveUiState.Idle)
    val uiState: StateFlow<ReserveUiState> = _uiState

    // Estado actual del formulario
    private val _formState = MutableStateFlow(ReserveFormState())
    val formState: StateFlow<ReserveFormState> = _formState

    init {
        loadReserves()
    }

    /**
     * Carga todas las reservas desde el repositorio.
     */
    fun loadReserves() {
        viewModelScope.launch {
            _uiState.value = ReserveUiState.Loading
            try {
                val list = repo.getAllReserves()
                _uiState.value = ReserveUiState.Loaded(list)
            } catch (e: Exception) {
                _uiState.value = ReserveUiState.Error(e.message.orEmpty())
            }
        }
    }

    /**
     * Actualiza el estado del formulario.
     */
    fun updateForm(update: ReserveFormState.() -> ReserveFormState) {
        _formState.value = _formState.value.update()
    }

    /**
     * Crea una nueva reserva usando el estado actual del formulario.
     */
    fun createReserve() {
        val form = _formState.value
        if (!form.isValid) {
            _uiState.value = ReserveUiState.Error("Completa todos los campos")
            return
        }
        viewModelScope.launch {
            _uiState.value = ReserveUiState.Loading
            try {
                val newReserve = Reserve(
                    id = null,
                    nombreHuespedes = form.nombreHuespedes,
                    habitacion      = form.habitacion,
                    horaIngreso     = form.horaIngreso,
                    horaSalida      = form.horaSalida,
                    // fechaCreacion la pone la BD / backend
                )
                repo.createReserve(newReserve)
                _uiState.value = ReserveUiState.Success("Reserva creada")
                loadReserves()
                _formState.value = ReserveFormState()
            } catch (e: Exception) {
                _uiState.value = ReserveUiState.Error(e.message.orEmpty())
            }
        }
    }

    /**
     * Elimina una reserva por su ID.
     */
    fun deleteReserve(id: Long) {
        viewModelScope.launch {
            _uiState.value = ReserveUiState.Loading
            try {
                repo.deleteReserve(id)
                _uiState.value = ReserveUiState.Success("Reserva eliminada")
                loadReserves()
            } catch (e: Exception) {
                _uiState.value = ReserveUiState.Error(e.message.orEmpty())
            }
        }
    }
}
