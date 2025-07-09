package pe.edu.upc.logisticmaster.data.repository

import pe.edu.upc.logisticmaster.domain.model.Task
import pe.edu.upc.logisticmaster.data.remote.api.TaskApiService
import pe.edu.upc.logisticmaster.data.remote.dto.TaskDto
import kotlin.collections.map

class TaskRepositoryImpl(
    private val api: TaskApiService
) : TaskRepository {

    override suspend fun getAllTasks(): List<Task> =
        api.getAll().map { it.toDomain() }

    override suspend fun getTaskById(id: Long): Task =
        api.getById(id).toDomain()

    override suspend fun createTask(task: Task): Task =
        api.create(task.toDto()).toDomain()

    override suspend fun updateTask(id: Long, task: Task): Task =
        api.update(id, task.toDto()).toDomain()

    override suspend fun deleteTask(id: Long) {
        api.delete(id)
    }

    // Mappers
    private fun TaskDto.toDomain() = Task(
        id = this.id,
        titulo = this.titulo,
        descripcion = this.descripcion,
        workerId = this.workerId
    )

    private fun Task.toDto() = TaskDto(
        id = this.id,
        titulo = this.titulo,
        descripcion = this.descripcion,
        workerId = this.workerId
    )
}
