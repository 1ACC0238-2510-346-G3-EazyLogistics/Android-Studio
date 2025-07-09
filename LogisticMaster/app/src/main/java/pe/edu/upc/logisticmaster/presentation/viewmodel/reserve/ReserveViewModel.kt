package pe.edu.upc.logisticmaster.presentation.viewmodel.reserve

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.logisticmaster.data.repository.ReserveRepository
import pe.edu.upc.logisticmaster.domain.model.Reserve

class ReserveViewModel(
    private val repo: ReserveRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ReserveUiState>(ReserveUiState.Idle)
    val uiState: StateFlow<ReserveUiState> = _uiState

    private val _form = MutableStateFlow(ReserveFormState())
    val formState: StateFlow<ReserveFormState> = _form

    init { loadReserves() }

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

    fun updateForm(update: ReserveFormState.() -> ReserveFormState) {
        _form.value = _form.value.update()
    }

    fun createReserve() {
        val f = _form.value
        if (!f.isValid) {
            _uiState.value = ReserveUiState.Error("Completa todos los campos")
            return
        }
        viewModelScope.launch {
            _uiState.value = ReserveUiState.Loading
            try {
                repo.createReserve(
                    Reserve(
                        id               = null,
                        nombreHuespedes  = f.nombreHuespedes,
                        habitacion       = f.habitacion,
                        horaIngreso      = f.horaIngreso,
                        horaSalida       = f.horaSalida
                    )
                )
                _uiState.value = ReserveUiState.Success("Reserva creada")
                loadReserves()
                _form.value = ReserveFormState()
            } catch (e: Exception) {
                _uiState.value = ReserveUiState.Error(e.message.orEmpty())
            }
        }
    }

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
