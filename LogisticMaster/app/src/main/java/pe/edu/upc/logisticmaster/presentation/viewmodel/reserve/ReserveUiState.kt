package pe.edu.upc.logisticmaster.presentation.viewmodel.reserve

import pe.edu.upc.logisticmaster.domain.model.Reserve

/**
 * Estados de UI para la gestión de reservas.
 */
sealed class ReserveUiState {
    object Idle : ReserveUiState()                     // sin acción
    object Loading : ReserveUiState()                  // cargando
    data class Loaded(val reserves: List<Reserve>) : ReserveUiState()  // lista de reservas cargada
    data class LoadedSingle(val reserve: Reserve) : ReserveUiState()  // reserva individual cargada
    data class Error(val message: String) : ReserveUiState()       // ocurrió un error
}
