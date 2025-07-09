package pe.edu.upc.logisticmaster.data.di

import pe.edu.upc.logisticmaster.data.remote.api.AuthApiService
import pe.edu.upc.logisticmaster.data.remote.api.WorkerApiService
import pe.edu.upc.logisticmaster.data.remote.api.TaskApiService
import pe.edu.upc.logisticmaster.data.remote.api.ReserveApiService
import pe.edu.upc.logisticmaster.data.repository.WorkerRepositoryImpl
import pe.edu.upc.logisticmaster.data.repository.TaskRepositoryImpl
import pe.edu.upc.logisticmaster.data.repository.ReserveRepositoryImpl
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
    val authApiService    = retrofit.create(AuthApiService::class.java)
    val workerApiService  = retrofit.create(WorkerApiService::class.java)
    val taskApiService    = retrofit.create(TaskApiService::class.java)
    val reserveApiService = retrofit.create(ReserveApiService::class.java)

    /** Repositorios */
    val workerRepository  = WorkerRepositoryImpl(workerApiService)
    val taskRepository    = TaskRepositoryImpl(taskApiService)
    val reserveRepository = ReserveRepositoryImpl(reserveApiService)
}
