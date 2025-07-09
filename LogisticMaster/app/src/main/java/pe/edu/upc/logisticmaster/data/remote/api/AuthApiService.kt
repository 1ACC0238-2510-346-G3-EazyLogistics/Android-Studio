package pe.edu.upc.logisticmaster.data.remote.api

import pe.edu.upc.logisticmaster.data.remote.dto.LoginRequest
import pe.edu.upc.logisticmaster.data.remote.dto.RegisterRequest
import pe.edu.upc.logisticmaster.data.remote.dto.UsuarioDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("/api/users/login")
    suspend fun login(@Body body: LoginRequest): UsuarioDto

    @POST("/api/users")
    suspend fun register(@Body body: RegisterRequest): UsuarioDto
}
