package pe.edu.upc.logisticmaster.data.remote.api

import pe.edu.upc.logisticmaster.data.remote.dto.TaskDto
import pe.edu.upc.logisticmaster.data.remote.dto.WorkerDto
import retrofit2.http.*

interface WorkerApiService {
    @GET("/api/workers")
    suspend fun getAll(): List<WorkerDto>

    @GET("/api/workers/{id}")
    suspend fun getById(@Path("id") id: Long): WorkerDto

    @GET("/api/tasks/worker/{workerId}")
    suspend fun getByWorker(@Path("workerId") workerId: Long): List<TaskDto>

    @POST("/api/workers")
    suspend fun create(@Body body: WorkerDto): WorkerDto

    @PUT("/api/workers/{id}")
    suspend fun update(@Path("id") id: Long, @Body body: WorkerDto): WorkerDto

    @DELETE("/api/workers/{id}")
    suspend fun delete(@Path("id") id: Long)
}
