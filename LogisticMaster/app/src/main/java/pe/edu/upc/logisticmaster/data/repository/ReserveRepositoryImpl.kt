// ReserveRepositoryImpl.kt
package pe.edu.upc.logisticmaster.data.repository

import pe.edu.upc.logisticmaster.data.remote.api.ReserveApiService
import pe.edu.upc.logisticmaster.data.remote.dto.ReserveDto
import pe.edu.upc.logisticmaster.domain.model.Reserve

class ReserveRepositoryImpl(
    private val api: ReserveApiService
) : ReserveRepository {

    override suspend fun getAllReserves(): List<Reserve> =
        api.getAll().map { it.toDomain() }

    override suspend fun getReserveById(id: Long): Reserve =
        api.getById(id).toDomain()

    override suspend fun createReserve(reserve: Reserve): Reserve =
        api.create(reserve.toDto()).toDomain()

    override suspend fun updateReserve(id: Long, reserve: Reserve): Reserve =
        api.update(id, reserve.toDto()).toDomain()

    override suspend fun deleteReserve(id: Long) {
        api.delete(id)
    }

    // Mappers
    private fun ReserveDto.toDomain() = Reserve(
        id               = this.id,
        nombreHuespedes  = this.nombreHuespedes,
        habitacion       = this.habitacion,
        horaIngreso      = this.horaIngreso,
        horaSalida       = this.horaSalida
    )

    private fun Reserve.toDto() = ReserveDto(
        id               = this.id,
        nombreHuespedes  = this.nombreHuespedes,
        habitacion       = this.habitacion,
        horaIngreso      = this.horaIngreso,
        horaSalida       = this.horaSalida,
        fechaCreacion    = null  // lo genera el backend
    )
}

