package pe.edu.upc.logisticmaster.domain.model

data class User(
    val id: Long?,
    val usuario: String,
    val email: String,
    val nombre: String,
    val apellido: String,
    val password: String? = null
) 