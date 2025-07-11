package pe.edu.upc.logisticmaster.presentation.di

import pe.edu.upc.logisticmaster.data.di.AppModule
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.AuthViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.task.TaskViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.reserve.ReserveViewModel

/**
 * Módulo centralizado para construir repos y ViewModels sin Hilt.
 */
object ViewModelModule {

    // --- Proveedores de ViewModel ---
    fun provideAuthViewModel()     = AuthViewModel(AppModule.userRepository)
    fun provideWorkerViewModel()   = WorkerViewModel(AppModule.workerRepository)
    fun provideTaskViewModel()     = TaskViewModel(AppModule.taskRepository)
    fun provideReserveViewModel()  = ReserveViewModel(AppModule.reserveRepository)
}
