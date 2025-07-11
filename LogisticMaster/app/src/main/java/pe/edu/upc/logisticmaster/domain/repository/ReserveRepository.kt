package pe.edu.upc.logisticmaster.domain.repository

import pe.edu.upc.logisticmaster.domain.model.Reserve

interface ReserveRepository {
    suspend fun getAllReserves(): List<Reserve>
    suspend fun getReserveById(id: Long): Reserve
} 