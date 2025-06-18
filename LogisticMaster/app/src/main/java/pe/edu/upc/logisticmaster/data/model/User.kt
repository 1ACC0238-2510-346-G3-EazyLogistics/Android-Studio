package pe.edu.upc.logisticmaster.data.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val profilePictureUrl: String? = null,
    val isActive: Boolean = true
)
