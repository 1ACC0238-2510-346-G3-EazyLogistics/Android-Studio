package pe.edu.upc.logisticmaster.domain.repository

import pe.edu.upc.logisticmaster.domain.model.User

interface UserRepository {
    suspend fun register(user: User): User
    suspend fun login(username: String): User
    suspend fun updateUser(id: Long, user: User): User
} 