package pe.edu.upc.logisticmaster.domain.repository

import pe.edu.upc.logisticmaster.domain.model.Task

interface TaskRepository {
    suspend fun getAllTasks(): List<Task>
    suspend fun getTaskById(id: Long): Task
    suspend fun getTasksByWorker(workerId: Long): List<Task>
    suspend fun createTask(task: Task): Task
    suspend fun updateTask(task: Task): Task
    suspend fun deleteTask(id: Long)
} 