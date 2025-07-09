package pe.edu.upc.logisticmaster.presentation.viewmodel.task

import pe.edu.upc.logisticmaster.domain.model.Task

/**
 * Estados de UI para la gestión de tareas.
 */
sealed class TaskUiState {
    object Idle : TaskUiState()                             // sin acción
    object Loading : TaskUiState()                          // cargando
    data class Loaded(val list: List<Task>) : TaskUiState() // lista de tareas cargada
    data class Success(val message: String) : TaskUiState() // operación OK
    data class Error(val message: String) : TaskUiState()   // ocurrió un error
}
