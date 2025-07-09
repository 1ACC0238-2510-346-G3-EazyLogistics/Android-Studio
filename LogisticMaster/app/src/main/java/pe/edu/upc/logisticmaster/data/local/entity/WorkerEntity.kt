package pe.edu.upc.logisticmaster.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workers")
data class WorkerEntity(
    @PrimaryKey val id: Int,
    val nombre: String,
    val email: String,
    val telefono: String,
    val puesto: String,
    val area: String
)
