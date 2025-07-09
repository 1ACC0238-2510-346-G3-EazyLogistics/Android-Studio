package pe.edu.upc.logisticmaster.presentation.di

import pe.edu.upc.logisticmaster.data.di.AppModule
import pe.edu.upc.logisticmaster.data.remote.api.TaskApiService
import pe.edu.upc.logisticmaster.data.remote.api.ReserveApiService
import pe.edu.upc.logisticmaster.data.repository.TaskRepositoryImpl
import pe.edu.upc.logisticmaster.data.repository.ReserveRepositoryImpl
import pe.edu.upc.logisticmaster.data.repository.WorkerRepositoryImpl
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.AuthViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.reserve.ReserveViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.task.TaskViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerViewModel

/**
 * Módulo centralizado para construir repos y ViewModels sin Hilt.
 */
object ViewModelModule {

    // --- API clients desde AppModule ---
    private val authApi    = AppModule.authApiService
    private val workerApi  = AppModule.workerApiService
    private val taskApi    = AppModule.retrofit.create(TaskApiService::class.java)
    private val reserveApi = AppModule.retrofit.create(ReserveApiService::class.java)

    // --- Repositorios ---
    private val workerRepo  = WorkerRepositoryImpl(workerApi)
    private val taskRepo    = TaskRepositoryImpl(taskApi)
    private val reserveRepo = ReserveRepositoryImpl(reserveApi)

    // --- Proveedores de ViewModel ---
    fun provideAuthViewModel()     = AuthViewModel(authApi)
    fun provideWorkerViewModel()   = WorkerViewModel(workerRepo)
    fun provideTaskViewModel()     = TaskViewModel(taskRepo)
    fun provideReserveViewModel()  = ReserveViewModel(reserveRepo)
}
