package pe.edu.upc.logisticmaster.data.repository

import pe.edu.upc.logisticmaster.domain.model.User
import pe.edu.upc.logisticmaster.domain.repository.UserRepository
import pe.edu.upc.logisticmaster.data.remote.api.UserApiService
import pe.edu.upc.logisticmaster.data.remote.dto.UserDto

class UserRepositoryImpl(
    private val api: UserApiService
) : UserRepository {

    override suspend fun register(user: User): User {
        val dto = user.toDto()
        val saved = api.register(dto)
        return saved.toDomain()
    }

    override suspend fun login(username: String): User {
        return api.getByUsername(username).toDomain()
    }

    override suspend fun updateUser(id: Long, user: User): User {
        val dto = user.toDto()
        val updated = api.update(id, dto)
        return updated.toDomain()
    }

    // Mappers
    private fun User.toDto() = UserDto(
        id = this.id,
        usuario = this.usuario,
        email = this.email,
        nombre = this.nombre,
        apellido = this.apellido,
        password = this.password
    )

    private fun UserDto.toDomain() = User(
        id = this.id,
        usuario = this.usuario,
        email = this.email,
        nombre = this.nombre,
        apellido = this.apellido,
        password = this.password
    )
} 