package pe.edu.upc.logisticmaster.presentation.viewmodel.worker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.logisticmaster.domain.model.Worker
import pe.edu.upc.logisticmaster.data.repository.WorkerRepository

/**
 * ViewModel para CRUD de trabajadores.
 */
class WorkerViewModel(
    private val repo: WorkerRepository
) : ViewModel() {

    // Estado general de la UI (lista, loading, error, success)
    private val _uiState = MutableStateFlow<WorkerUiState>(WorkerUiState.Idle)
    val uiState: StateFlow<WorkerUiState> = _uiState

    // Estado actual del formulario
    private val _formState = MutableStateFlow(WorkerFormState())
    val formState: StateFlow<WorkerFormState> = _formState

    init {
        loadWorkers()
    }

    /** Carga todos los trabajadores */
    fun loadWorkers() {
        viewModelScope.launch {
            _uiState.value = WorkerUiState.Loading
            try {
                val list = repo.getAllWorkers()
                _uiState.value = WorkerUiState.Loaded(list)
            } catch (e: Exception) {
                _uiState.value = WorkerUiState.Error(e.message.orEmpty())
            }
        }
    }

    /** Actualiza el estado del formulario de worker */
    fun updateForm(update: WorkerFormState.() -> WorkerFormState) {
        _formState.value = _formState.value.update()
    }

    /** Crea un nuevo trabajador usando los datos del formulario */
    fun createWorker() {
        val form = _formState.value
        if (!form.isValid) {
            _uiState.value = WorkerUiState.Error("Completa todos los campos")
            return
        }
        viewModelScope.launch {
            _uiState.value = WorkerUiState.Loading
            try {
                val newWorker = Worker(
                    id = null,
                    nombre   = form.nombre,
                    apellido = form.apellido,
                    email    = form.email,
                    telefono = form.telefono,
                    puesto   = form.puesto,
                    area     = form.area
                )
                repo.createWorker(newWorker)
                _uiState.value = WorkerUiState.Success("Empleado creado")
                loadWorkers()
                _formState.value = WorkerFormState()
            } catch (e: Exception) {
                _uiState.value = WorkerUiState.Error(e.message.orEmpty())
            }
        }
    }

    /** Actualiza un trabajador existente */
    fun updateWorker(id: Long) {
        val form = _formState.value
        if (!form.isValid) {
            _uiState.value = WorkerUiState.Error("Completa todos los campos")
            return
        }
        viewModelScope.launch {
            _uiState.value = WorkerUiState.Loading
            try {
                val updated = Worker(
                    id       = id,
                    nombre   = form.nombre,
                    apellido = form.apellido,
                    email    = form.email,
                    telefono = form.telefono,
                    puesto   = form.puesto,
                    area     = form.area
                )
                repo.updateWorker(id, updated)
                _uiState.value = WorkerUiState.Success("Empleado actualizado")
                loadWorkers()
            } catch (e: Exception) {
                _uiState.value = WorkerUiState.Error(e.message.orEmpty())
            }
        }
    }

    /** Elimina un trabajador por su ID */
    fun deleteWorker(id: Long) {
        viewModelScope.launch {
            _uiState.value = WorkerUiState.Loading
            try {
                repo.deleteWorker(id)
                _uiState.value = WorkerUiState.Success("Empleado eliminado")
                loadWorkers()
            } catch (e: Exception) {
                _uiState.value = WorkerUiState.Error(e.message.orEmpty())
            }
        }
    }
}
