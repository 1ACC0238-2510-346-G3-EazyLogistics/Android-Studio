package pe.edu.upc.logisticmaster.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    val id: Long?,
    val usuario: String,
    val email: String,
    val nombre: String,
    val apellido: String,
    @SerializedName("contrasena") val contraseña: String? = null //sera esto?
) 