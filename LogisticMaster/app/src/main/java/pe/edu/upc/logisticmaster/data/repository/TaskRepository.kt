package pe.edu.upc.logisticmaster.data.repository

import kotlinx.coroutines.flow.Flow
import pe.edu.upc.logisticmaster.domain.model.Task
import pe.edu.upc.logisticmaster.data.remote.dto.TaskDto

interface TaskRepository {
    suspend fun getAllTasks(): List<Task>
    suspend fun getTaskById(id: Long): Task
    suspend fun createTask(task: Task): Task
    suspend fun updateTask(id: Long, task: Task): Task
    suspend fun deleteTask(id: Long)
}
