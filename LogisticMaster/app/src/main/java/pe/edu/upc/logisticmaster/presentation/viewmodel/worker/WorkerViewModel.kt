package pe.edu.upc.logisticmaster.presentation.viewmodel.worker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.logisticmaster.domain.repository.WorkerRepository
import pe.edu.upc.logisticmaster.domain.model.Worker

class WorkerViewModel(
    private val workerRepository: WorkerRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<WorkerUiState>(WorkerUiState.Idle)
    val uiState: StateFlow<WorkerUiState> = _uiState

    private val _formState = MutableStateFlow(WorkerFormState())
    val formState: StateFlow<WorkerFormState> = _formState



    fun loadWorkers() {
        viewModelScope.launch {
            _uiState.value = WorkerUiState.Loading
            try {
                val workers = workerRepository.getAllWorkers()
                _uiState.value = WorkerUiState.Loaded(workers)
            } catch (e: Exception) {
                _uiState.value = WorkerUiState.Error(e.message ?: "Error al cargar empleados")
            }
        }
    }

    fun loadWorkerById(id: Long) {
        viewModelScope.launch {
            _uiState.value = WorkerUiState.Loading
            try {
                val worker = workerRepository.getWorkerById(id)
                _formState.value = WorkerFormState(
                    nombre = worker.nombre,
                    apellido = worker.apellido,
                    puesto = worker.puesto,
                    area = worker.area,
                    email = worker.email,
                    telefono = worker.telefono
                )
                _uiState.value = WorkerUiState.Loaded(listOf(worker))
            } catch (e: Exception) {
                _uiState.value = WorkerUiState.Error(e.message ?: "Error al cargar empleado")
            }
        }
    }

    fun createWorker() {
        val form = _formState.value
        if (form.nombre.isBlank() || form.apellido.isBlank() || form.puesto.isBlank() || form.area.isBlank() || form.email.isBlank() || form.telefono.isBlank()) {
            _uiState.value = WorkerUiState.Error("Todos los campos son requeridos")
            return
        }

        viewModelScope.launch {
            _uiState.value = WorkerUiState.Loading
            try {
                val worker = Worker(
                    id = null,
                    nombre = form.nombre,
                    apellido = form.apellido,
                    puesto = form.puesto,
                    area = form.area,
                    email = form.email,
                    telefono = form.telefono
                )
                val createdWorker = workerRepository.createWorker(worker)
                _uiState.value = WorkerUiState.Success("Empleado creado exitosamente")
                clearForm()
            } catch (e: Exception) {
                _uiState.value = WorkerUiState.Error(e.message ?: "Error al crear empleado")
            }
        }
    }

    fun updateWorker(id: Long) {
        val form = _formState.value
        if (form.nombre.isBlank() || form.apellido.isBlank() || form.puesto.isBlank() || form.area.isBlank() || form.email.isBlank() || form.telefono.isBlank()) {
            _uiState.value = WorkerUiState.Error("Todos los campos son requeridos")
            return
        }

        viewModelScope.launch {
            _uiState.value = WorkerUiState.Loading
            try {
                val worker = Worker(
                    id = id,
                    nombre = form.nombre,
                    apellido = form.apellido,
                    puesto = form.puesto,
                    area = form.area,
                    email = form.email,
                    telefono = form.telefono
                )
                val updatedWorker = workerRepository.updateWorker(worker)
                _uiState.value = WorkerUiState.Success("Empleado actualizado exitosamente")
                clearForm()
            } catch (e: Exception) {
                _uiState.value = WorkerUiState.Error(e.message ?: "Error al actualizar empleado")
            }
        }
    }

    fun deleteWorker(id: Long) {
        viewModelScope.launch {
            _uiState.value = WorkerUiState.Loading
            try {
                workerRepository.deleteWorker(id)
                _uiState.value = WorkerUiState.Success("Empleado eliminado exitosamente")
                loadWorkers()
            } catch (e: Exception) {
                _uiState.value = WorkerUiState.Error(e.message ?: "Error al eliminar empleado")
            }
        }
    }

    fun updateForm(update: (WorkerFormState) -> WorkerFormState) {
        _formState.value = update(_formState.value)
    }

    fun updateNombre(nombre: String) {
        _formState.value = _formState.value.copy(nombre = nombre)
    }

    fun updateApellido(apellido: String) {
        _formState.value = _formState.value.copy(apellido = apellido)
    }

    fun updatePuesto(puesto: String) {
        _formState.value = _formState.value.copy(puesto = puesto)
    }

    fun updateArea(area: String) {
        _formState.value = _formState.value.copy(area = area)
    }

    fun updateEmail(email: String) {
        _formState.value = _formState.value.copy(email = email)
    }

    fun updateTelefono(telefono: String) {
        _formState.value = _formState.value.copy(telefono = telefono)
    }

    private fun clearForm() {
        _formState.value = WorkerFormState()
    }

    fun clearError() {
        _uiState.value = WorkerUiState.Idle
    }
}

