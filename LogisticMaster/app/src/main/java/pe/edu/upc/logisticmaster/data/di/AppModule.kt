package pe.edu.upc.logisticmaster.data.di

import android.content.Context
import androidx.room.Room
import pe.edu.upc.logisticmaster.data.local.dao.WorkerDao
import pe.edu.upc.logisticmaster.data.local.db.AppDatabase
import pe.edu.upc.logisticmaster.data.remote.api.AuthApiService
import pe.edu.upc.logisticmaster.data.remote.api.WorkerApiService
import pe.edu.upc.logisticmaster.data.repository.WorkerRepository
import pe.edu.upc.logisticmaster.data.repository.WorkerRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    private const val BASE_URL = "http://10.0.2.2:8080"

    /** Retrofit singleton */
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /** API services */
    val authApiService: AuthApiService = retrofit.create(AuthApiService::class.java)
    val workerApiService: WorkerApiService = retrofit.create(WorkerApiService::class.java)

    /** Room database */
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_db")
            .fallbackToDestructiveMigration()
            .build()

    /** DAO */
    fun provideWorkerDao(db: AppDatabase): WorkerDao =
        db.workerDao()

    /** Repository */
    fun provideWorkerRepository(
        api: WorkerApiService,
        dao: WorkerDao
    ): WorkerRepository =
        WorkerRepositoryImpl(api, dao)
}
