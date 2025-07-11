// ReserveRepositoryImpl.kt
package pe.edu.upc.logisticmaster.data.repository

import pe.edu.upc.logisticmaster.domain.model.Reserve
import pe.edu.upc.logisticmaster.domain.repository.ReserveRepository
import pe.edu.upc.logisticmaster.data.remote.api.ReserveApiService
import pe.edu.upc.logisticmaster.data.remote.dto.ReserveDto

class ReserveRepositoryImpl(
    private val api: ReserveApiService
) : ReserveRepository {

    override suspend fun getAllReserves(): List<Reserve> {
        return api.getAll().map { it.toDomain() }
    }

    override suspend fun getReserveById(id: Long): Reserve {
        return api.getById(id).toDomain()
    }



    // Mappers
    private fun ReserveDto.toDomain() = Reserve(
        id = this.id,
        fechaEntrada = this.fechaEntrada,
        fechaSalida = this.fechaSalida,
        estado = this.estado,
        numeroHabitacion = this.numeroHabitacion,
        precio = this.precio,
        userId = this.userId,
        hotelId = this.hotelId
    )


}

