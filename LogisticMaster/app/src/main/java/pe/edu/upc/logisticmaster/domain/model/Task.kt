package pe.edu.upc.logisticmaster.domain.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val createdAt: String,
    val updatedAt: String,
    val assignedTo: String? = null,

)
