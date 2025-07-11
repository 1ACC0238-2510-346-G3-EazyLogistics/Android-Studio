package pe.edu.upc.logisticmaster.data.repository

import pe.edu.upc.logisticmaster.domain.model.Task
import pe.edu.upc.logisticmaster.domain.repository.TaskRepository
import pe.edu.upc.logisticmaster.data.remote.api.TaskApiService
import pe.edu.upc.logisticmaster.data.remote.dto.TaskDto

class TaskRepositoryImpl(
    private val api: TaskApiService
) : TaskRepository {

    override suspend fun getAllTasks(): List<Task> {
        return api.getAll().map { it.toDomain() }
    }

    override suspend fun getTaskById(id: Long): Task {
        return api.getById(id).toDomain()
    }

    override suspend fun getTasksByWorker(workerId: Long): List<Task> {
        return api.getByWorker(workerId).map { it.toDomain() }
    }

    override suspend fun createTask(task: Task): Task {
        val dto = task.toDto()
        val saved = api.create(dto)
        return saved.toDomain()
    }

    override suspend fun updateTask(task: Task): Task {
        val dto = task.toDto()
        val updated = api.update(task.id!!, dto)
        return updated.toDomain()
    }

    override suspend fun deleteTask(id: Long) {
        api.delete(id)
    }

    // Mappers
    private fun Task.toDto() = TaskDto(
        id = this.id,
        titulo = this.titulo,
        descripcion = this.descripcion,
        workerId = this.workerId
    )

    private fun TaskDto.toDomain() = Task(
        id = this.id,
        titulo = this.titulo,
        descripcion = this.descripcion,
        workerId = this.workerId
    )
}
