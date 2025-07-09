package pe.edu.upc.logisticmaster.data.remote.api

import pe.edu.upc.logisticmaster.data.remote.dto.TaskDto
import retrofit2.http.*

interface TaskApiService {
    @GET("/api/tasks")
    suspend fun getAll(): List<TaskDto>

    @GET("/api/tasks/{id}")
    suspend fun getById(@Path("id") id: Long): TaskDto

    @POST("/api/tasks")
    suspend fun create(@Body body: TaskDto): TaskDto

    @PUT("/api/tasks/{id}")
    suspend fun update(@Path("id") id: Long, @Body body: TaskDto): TaskDto

    @DELETE("/api/tasks/{id}")
    suspend fun delete(@Path("id") id: Long)
}
