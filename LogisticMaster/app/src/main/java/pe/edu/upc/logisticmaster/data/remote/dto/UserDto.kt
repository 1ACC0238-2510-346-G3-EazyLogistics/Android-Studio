package pe.edu.upc.logisticmaster.data.remote.dto

data class UserDto(
    val id: Long?,
    val usuario: String,
    val email: String,
    val nombre: String,
    val apellido: String,
    val password: String? = null //sera esto?
) 