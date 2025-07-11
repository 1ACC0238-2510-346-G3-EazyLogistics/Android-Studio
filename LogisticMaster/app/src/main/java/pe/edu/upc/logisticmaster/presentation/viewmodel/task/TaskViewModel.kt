package pe.edu.upc.logisticmaster.presentation.viewmodel.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.logisticmaster.domain.repository.TaskRepository
import pe.edu.upc.logisticmaster.domain.model.Task

class TaskViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<TaskUiState>(TaskUiState.Idle)
    val uiState: StateFlow<TaskUiState> = _uiState

    private val _formState = MutableStateFlow(TaskFormState())
    val formState: StateFlow<TaskFormState> = _formState

    fun loadAllTasks() {
        viewModelScope.launch {
            _uiState.value = TaskUiState.Loading
            try {
                val tasks = taskRepository.getAllTasks()
                _uiState.value = TaskUiState.Loaded(tasks)
            } catch (e: Exception) {
                _uiState.value = TaskUiState.Error(e.message ?: "Error al cargar tareas")
            }
        }
    }

    fun loadTaskById(id: Long) {
        viewModelScope.launch {
            _uiState.value = TaskUiState.Loading
            try {
                val task = taskRepository.getTaskById(id)
                _formState.value = TaskFormState(
                    titulo = task.titulo,
                    descripcion = task.descripcion,
                    workerId = task.workerId
                )
                _uiState.value = TaskUiState.Loaded(listOf(task))
            } catch (e: Exception) {
                _uiState.value = TaskUiState.Error(e.message ?: "Error al cargar tarea")
            }
        }
    }

    fun createTask() {
        val form = _formState.value
        if (form.titulo.isBlank() || form.descripcion.isBlank()) {
            _uiState.value = TaskUiState.Error("Título y descripción son requeridos")
            return
        }

        viewModelScope.launch {
            _uiState.value = TaskUiState.Loading
            try {
                val task = Task(
                    id = null,
                    titulo = form.titulo,
                    descripcion = form.descripcion,
                    workerId = form.workerId
                )
                val createdTask = taskRepository.createTask(task)
                _uiState.value = TaskUiState.Success("Tarea creada exitosamente")
                clearForm()
            } catch (e: Exception) {
                _uiState.value = TaskUiState.Error(e.message ?: "Error al crear tarea")
            }
        }
    }

    fun updateTask(id: Long) {
        val form = _formState.value
        if (form.titulo.isBlank() || form.descripcion.isBlank()) {
            _uiState.value = TaskUiState.Error("Título y descripción son requeridos")
            return
        }

        viewModelScope.launch {
            _uiState.value = TaskUiState.Loading
            try {
                val task = Task(
                    id = id,
                    titulo = form.titulo,
                    descripcion = form.descripcion,
                    workerId = form.workerId
                )
                val updatedTask = taskRepository.updateTask(task)
                _uiState.value = TaskUiState.Success("Tarea actualizada exitosamente")
                clearForm()
            } catch (e: Exception) {
                _uiState.value = TaskUiState.Error(e.message ?: "Error al actualizar tarea")
            }
        }
    }

    fun deleteTask(id: Long) {
        viewModelScope.launch {
            _uiState.value = TaskUiState.Loading
            try {
                taskRepository.deleteTask(id)
                _uiState.value = TaskUiState.Success("Tarea eliminada exitosamente")
                loadAllTasks()
            } catch (e: Exception) {
                _uiState.value = TaskUiState.Error(e.message ?: "Error al eliminar tarea")
            }
        }
    }

    fun updateForm(update: (TaskFormState) -> TaskFormState) {
        _formState.value = update(_formState.value)
    }

    fun updateTitulo(titulo: String) {
        _formState.value = _formState.value.copy(titulo = titulo)
    }

    fun updateDescripcion(descripcion: String) {
        _formState.value = _formState.value.copy(descripcion = descripcion)
    }

    fun updateWorkerId(workerId: Long?) {
        _formState.value = _formState.value.copy(workerId = workerId)
    }

    private fun clearForm() {
        _formState.value = TaskFormState()
    }

    fun clearError() {
        _uiState.value = TaskUiState.Idle
    }
}

