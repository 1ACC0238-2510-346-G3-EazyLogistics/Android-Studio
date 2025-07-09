// 3. UsuarioDto.kt
package pe.edu.upc.logisticmaster.data.remote.dto

data class UsuarioDto(
    val id: Long,
    val nombre: String,
    val apellido: String,
    val usuario: String,
    val email: String,
    val contrasena: String
)
