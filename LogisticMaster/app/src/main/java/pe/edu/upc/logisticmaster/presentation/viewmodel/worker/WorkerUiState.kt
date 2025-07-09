package pe.edu.upc.logisticmaster.presentation.viewmodel.worker

import pe.edu.upc.logisticmaster.domain.model.Worker

/**
 * Estados de UI para la gestión de trabajadores.
 */
sealed class WorkerUiState {
    object Idle : WorkerUiState()                          // sin acción
    object Loading : WorkerUiState()                       // cargando datos
    data class Loaded(val list: List<Worker>) : WorkerUiState() // lista de trabajadores
    data class Success(val message: String) : WorkerUiState()    // operación exitosa
    data class Error(val message: String) : WorkerUiState()      // error ocurrido
}
