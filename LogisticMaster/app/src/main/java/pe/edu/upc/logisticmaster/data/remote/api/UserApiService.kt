package pe.edu.upc.logisticmaster.data.remote.api

import pe.edu.upc.logisticmaster.data.remote.dto.UserDto
import retrofit2.http.*

interface UserApiService {
    @POST("/api/users")
    suspend fun register(@Body user: UserDto): UserDto

    @GET("/api/users/username/{usuario}")
    suspend fun getByUsername(@Path("usuario") usuario: String): UserDto

    @GET("/api/users/email/{email}")
    suspend fun getByEmail(@Path("email") email: String): UserDto

    @PUT("/api/users/{id}")
    suspend fun update(@Path("id") id: Long, @Body user: UserDto): UserDto
} 