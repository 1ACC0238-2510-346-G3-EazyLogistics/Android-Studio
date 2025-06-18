package pe.edu.upc.logisticmaster.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val phoneNumber: String? = null,
    val profilePictureUrl: String? = null,
    val isActive: Boolean = true
)
