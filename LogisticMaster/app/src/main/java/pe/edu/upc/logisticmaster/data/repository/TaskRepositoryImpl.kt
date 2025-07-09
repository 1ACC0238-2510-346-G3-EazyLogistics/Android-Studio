package pe.edu.upc.logisticmaster.data.repository

import pe.edu.upc.logisticmaster.domain.model.Task
import pe.edu.upc.logisticmaster.data.remote.api.TaskApiService

class TaskRepositoryImpl(
    private val api: TaskApiService
) : TaskRepository {

    override suspend fun getAllTasks(): List<Task> =
        api.getAll().map { it.toDomain() }

    override suspend fun getTasksByWorker(workerId: Long): List<Task> =
        api.getByWorker(workerId).map { it.toDomain() }


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
    private fun pe.edu.upc.logisticmaster.data.remote.dto.TaskDto.toDomain() = Task(
        id          = this.id ?: 0L,
        titulo      = this.titulo,
        descripcion = this.descripcion,
        workerId    = this.workerId
    )

    private fun Task.toDto() = pe.edu.upc.logisticmaster.data.remote.dto.TaskDto(
        id          = this.id,
        titulo      = this.titulo,
        descripcion = this.descripcion,
        workerId    = this.workerId
    )
}
