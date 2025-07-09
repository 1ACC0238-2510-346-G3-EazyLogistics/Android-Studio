package pe.edu.upc.logisticmaster.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import pe.edu.upc.logisticmaster.data.local.entity.WorkerEntity

@Dao
interface WorkerDao {

    @Query("SELECT * FROM workers")
    fun getAll(): Flow<List<WorkerEntity>>

    @Query("SELECT * FROM workers WHERE id = :id")
    suspend fun getById(id: Long): WorkerEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: WorkerEntity)

    @Delete
    suspend fun delete(entity: WorkerEntity)
}
