package pe.edu.upc.logisticmaster.data.repository

import pe.edu.upc.logisticmaster.domain.model.Reserve

interface ReserveRepository {
    suspend fun getAllReserves(): List<Reserve>
    suspend fun getReserveById(id: Long): Reserve
    suspend fun createReserve(reserve: Reserve): Reserve
    suspend fun updateReserve(id: Long, reserve: Reserve): Reserve
    suspend fun deleteReserve(id: Long)
}
