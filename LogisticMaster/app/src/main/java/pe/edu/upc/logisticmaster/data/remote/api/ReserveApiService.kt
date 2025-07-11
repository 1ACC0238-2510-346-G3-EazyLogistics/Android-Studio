// data/remote/api/ReserveApiService.kt
package pe.edu.upc.logisticmaster.data.remote.api

import pe.edu.upc.logisticmaster.data.remote.dto.ReserveDto
import retrofit2.http.*

interface ReserveApiService {
    @GET("/api/reserves")
    suspend fun getAll(): List<ReserveDto>

    @GET("/api/reserves/{id}")
    suspend fun getById(@Path("id") id: Long): ReserveDto
}
