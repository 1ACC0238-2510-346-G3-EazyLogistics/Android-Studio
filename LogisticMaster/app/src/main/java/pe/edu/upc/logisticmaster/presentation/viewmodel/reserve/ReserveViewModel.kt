package pe.edu.upc.logisticmaster.presentation.viewmodel.reserve

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.logisticmaster.domain.repository.ReserveRepository

class ReserveViewModel(
    private val reserveRepository: ReserveRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ReserveUiState>(ReserveUiState.Idle)
    val uiState: StateFlow<ReserveUiState> = _uiState

    fun loadAllReserves() {
        viewModelScope.launch {
            _uiState.value = ReserveUiState.Loading
            try {
                val reserves = reserveRepository.getAllReserves()
                _uiState.value = ReserveUiState.Loaded(reserves)
            } catch (e: Exception) {
                _uiState.value = ReserveUiState.Error(e.message ?: "Error al cargar reservas")
            }
        }
    }

    fun loadReserveById(id: Long) {
        viewModelScope.launch {
            _uiState.value = ReserveUiState.Loading
            try {
                val reserve = reserveRepository.getReserveById(id)
                _uiState.value = ReserveUiState.LoadedSingle(reserve)
            } catch (e: Exception) {
                _uiState.value = ReserveUiState.Error(e.message ?: "Error al cargar reserva")
            }
        }
    }

    fun clearError() {
        _uiState.value = ReserveUiState.Idle
    }
}
