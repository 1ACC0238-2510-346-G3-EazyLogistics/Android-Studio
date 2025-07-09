package pe.edu.upc.logisticmaster.presentation.viewmodel.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.logisticmaster.data.repository.TaskRepository
import pe.edu.upc.logisticmaster.domain.model.Task

class TaskViewModel(
    private val repo: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<TaskUiState>(TaskUiState.Idle)
    val uiState: StateFlow<TaskUiState> = _uiState

    private val _form = MutableStateFlow(TaskFormState())
    val formState: StateFlow<TaskFormState> = _form

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

    fun updateForm(update: TaskFormState.() -> TaskFormState) {
        _form.value = _form.value.update()
    }

    fun createTask() {
        val f = _form.value
        if (!f.isValid) {
            _uiState.value = TaskUiState.Error("Completa todos los campos")
            return
        }
        viewModelScope.launch {
            _uiState.value = TaskUiState.Loading
            try {
                repo.createTask(
                    Task(
                        id          = null,
                        titulo      = f.titulo,
                        descripcion = f.descripcion,
                        workerId    = f.workerId!!
                    )
                )
                _uiState.value = TaskUiState.Success("Tarea creada")
                loadTasksForWorker(f.workerId!!)
                _form.value = TaskFormState()
            } catch (e: Exception) {
                _uiState.value = TaskUiState.Error(e.message.orEmpty())
            }
        }
    }

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
