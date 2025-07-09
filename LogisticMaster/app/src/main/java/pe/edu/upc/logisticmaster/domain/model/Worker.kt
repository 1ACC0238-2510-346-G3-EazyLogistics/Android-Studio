package pe.edu.upc.logisticmaster.domain.model

data class Worker(
    val id: Int,
    val nombre: String,
    val email: String,
    val telefono: String,
    val puesto: String,
    val area: String
)
