// data/repository/WorkerRepository.kt
package pe.edu.upc.logisticmaster.data.repository

import pe.edu.upc.logisticmaster.domain.model.Worker

interface WorkerRepository {
    suspend fun getAllWorkers(): List<Worker>
    suspend fun getWorkerById(id: Long): Worker
    suspend fun createWorker(worker: Worker): Worker
    suspend fun updateWorker(id: Long, worker: Worker): Worker
    suspend fun deleteWorker(id: Long)
}
