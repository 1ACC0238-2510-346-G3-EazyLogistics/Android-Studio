package pe.edu.upc.logisticmaster.presentation.viewmodel.worker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.logisticmaster.data.repository.WorkerRepository

class WorkerViewModel(
    private val repo: WorkerRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<WorkerUiState>(WorkerUiState.Idle)
    val uiState: StateFlow<WorkerUiState> = _uiState

    private val _form = MutableStateFlow(WorkerFormState())
    val formState: StateFlow<WorkerFormState> = _form

    init { loadWorkers() }

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

    fun updateForm(update: WorkerFormState.() -> WorkerFormState) {
        _form.value = _form.value.update()
    }

    fun createWorker() {
        val f = _form.value
        if (!f.isValid) {
            _uiState.value = WorkerUiState.Error("Completa todos los campos")
            return
        }
        viewModelScope.launch {
            _uiState.value = WorkerUiState.Loading
            try {
                repo.createWorker(
                    pe.edu.upc.logisticmaster.domain.model.Worker(
                        id       = null,
                        nombre   = f.nombre,
                        apellido = f.apellido,
                        email    = f.email,
                        telefono = f.telefono,
                        puesto   = f.puesto,
                        area     = f.area
                    )
                )
                _uiState.value = WorkerUiState.Success("Empleado creado")
                loadWorkers()
                _form.value = WorkerFormState()
            } catch (e: Exception) {
                _uiState.value = WorkerUiState.Error(e.message.orEmpty())
            }
        }
    }

    fun updateWorker(id: Long) {
        val f = _form.value
        if (!f.isValid) {
            _uiState.value = WorkerUiState.Error("Completa todos los campos")
            return
        }
        viewModelScope.launch {
            _uiState.value = WorkerUiState.Loading
            try {
                repo.updateWorker(
                    id,
                    pe.edu.upc.logisticmaster.domain.model.Worker(
                        id       = id,
                        nombre   = f.nombre,
                        apellido = f.apellido,
                        email    = f.email,
                        telefono = f.telefono,
                        puesto   = f.puesto,
                        area     = f.area
                    )
                )
                _uiState.value = WorkerUiState.Success("Empleado actualizado")
                loadWorkers()
            } catch (e: Exception) {
                _uiState.value = WorkerUiState.Error(e.message.orEmpty())
            }
        }
    }

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
