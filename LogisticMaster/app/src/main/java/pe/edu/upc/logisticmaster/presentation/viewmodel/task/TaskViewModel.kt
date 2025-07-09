package pe.edu.upc.logisticmaster.presentation.viewmodel.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.logisticmaster.domain.model.Task
import pe.edu.upc.logisticmaster.data.repository.TaskRepository

/**
 * ViewModel para CRUD de tareas asociadas a un trabajador.
 */
class TaskViewModel(
    private val repo: TaskRepository
) : ViewModel() {

    // Estado general de la UI (lista, loading, error, success)
    private val _uiState = MutableStateFlow<TaskUiState>(TaskUiState.Idle)
    val uiState: StateFlow<TaskUiState> = _uiState

    // Estado actual del formulario
    private val _formState = MutableStateFlow(TaskFormState())
    val formState: StateFlow<TaskFormState> = _formState

    /**
     * Carga las tareas de un trabajador dado.
     */
    fun loadTasksForWorker(workerId: Long) {
        viewModelScope.launch {
            _uiState.value = TaskUiState.Loading
            try {
                val list = repo.getTasksByWorker(workerId)
                _uiState.value = TaskUiState.Loaded(list)
            } catch (e: Exception) {
                _uiState.value = TaskUiState.Error(e.message.orEmpty())
            }
        }
    }

    /**
     * Actualiza el estado del formulario.
     */
    fun updateForm(update: TaskFormState.() -> TaskFormState) {
        _formState.value = _formState.value.update()
    }

    /**
     * Crea una nueva tarea usando el estado actual del formulario.
     */
    fun createTask() {
        val form = _formState.value
        if (!form.isValid) {
            _uiState.value = TaskUiState.Error("Completa todos los campos")
            return
        }
        viewModelScope.launch {
            _uiState.value = TaskUiState.Loading
            try {
                val newTask = Task(
                    id = null,
                    titulo = form.titulo,
                    descripcion = form.descripcion,
                    workerId = form.workerId!!
                )
                repo.createTask(newTask)
                _uiState.value = TaskUiState.Success("Tarea creada")
                // refresca lista
                loadTasksForWorker(form.workerId!!)
                // limpia formulario
                _formState.value = TaskFormState()
            } catch (e: Exception) {
                _uiState.value = TaskUiState.Error(e.message.orEmpty())
            }
        }
    }

    /**
     * Actualiza una tarea existente.
     */
    fun updateTask(id: Long) {
        val form = _formState.value
        if (!form.isValid) {
            _uiState.value = TaskUiState.Error("Completa todos los campos")
            return
        }
        viewModelScope.launch {
            _uiState.value = TaskUiState.Loading
            try {
                val updated = Task(
                    id = id,
                    titulo = form.titulo,
                    descripcion = form.descripcion,
                    workerId = form.workerId!!
                )
                repo.updateTask(id, updated)
                _uiState.value = TaskUiState.Success("Tarea actualizada")
                loadTasksForWorker(form.workerId!!)
            } catch (e: Exception) {
                _uiState.value = TaskUiState.Error(e.message.orEmpty())
            }
        }
    }

    /**
     * Elimina una tarea por su ID.
     */
    fun deleteTask(id: Long, workerId: Long) {
        viewModelScope.launch {
            _uiState.value = TaskUiState.Loading
            try {
                repo.deleteTask(id)
                _uiState.value = TaskUiState.Success("Tarea eliminada")
                loadTasksForWorker(workerId)
            } catch (e: Exception) {
                _uiState.value = TaskUiState.Error(e.message.orEmpty())
            }
        }
    }
}
