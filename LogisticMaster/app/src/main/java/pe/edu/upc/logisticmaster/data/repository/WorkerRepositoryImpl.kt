package pe.edu.upc.logisticmaster.data.repository

import pe.edu.upc.logisticmaster.domain.model.Worker
import pe.edu.upc.logisticmaster.domain.repository.WorkerRepository
import pe.edu.upc.logisticmaster.data.remote.api.WorkerApiService
import pe.edu.upc.logisticmaster.data.remote.dto.WorkerDto

class WorkerRepositoryImpl(
    private val api: WorkerApiService
) : WorkerRepository {

    override suspend fun getAllWorkers(): List<Worker> {
        return api.getAll().map { it.toDomain() }
    }

    override suspend fun getWorkerById(id: Long): Worker {
        return api.getById(id).toDomain()
    }

    override suspend fun createWorker(worker: Worker): Worker {
        val dto = worker.toDto()
        val saved = api.create(dto)
        return saved.toDomain()
    }

    override suspend fun updateWorker(worker: Worker): Worker {
        val dto = worker.toDto()
        val updated = api.update(worker.id!!, dto)
        return updated.toDomain()
    }

    override suspend fun deleteWorker(id: Long) {
        api.delete(id)
    }

    // Mappers
    private fun Worker.toDto() = WorkerDto(
        id = this.id,
        nombre = this.nombre,
        apellido = this.apellido,
        puesto = this.puesto,
        area = this.area,
        email = this.email,
        telefono = this.telefono
    )

    private fun WorkerDto.toDomain() = Worker(
        id = this.id,
        nombre = this.nombre,
        apellido = this.apellido,
        puesto = this.puesto,
        area = this.area,
        email = this.email,
        telefono = this.telefono
    )
}
