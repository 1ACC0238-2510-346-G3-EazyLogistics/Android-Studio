package pe.edu.upc.logisticmaster.domain.model

data class Employee(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val role: String,
    val isActive: Boolean
)
