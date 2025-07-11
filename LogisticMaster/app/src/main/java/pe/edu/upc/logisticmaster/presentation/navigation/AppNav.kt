package pe.edu.upc.logisticmaster.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pe.edu.upc.logisticmaster.presentation.view.*
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.AuthViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.task.TaskViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.reserve.ReserveViewModel

@Composable
fun AppNav(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    workerViewModel: WorkerViewModel,
    taskViewModel: TaskViewModel,
    reserveViewModel: ReserveViewModel
) {
    NavHost(navController, startDestination = Routes.Login.route) {
        
        // Authentication
        composable(Routes.Login.route) {
            LoginScreen(navController, authViewModel)
        }
        composable(Routes.Register.route) {
            RegisterUserView(navController, authViewModel)
        }

        // Main Dashboard
        composable(Routes.Menu.route) {
            MainMenuScreen(navController, authViewModel)
        }
        composable(Routes.Profile.route) {
            UserProfileScreen(navController, authViewModel)
        }

        // Worker Management
        composable(Routes.PersonalManagement.route) {
            PersonalManagementScreen(navController, workerViewModel)
        }
        composable(Routes.AddEmployee.route) {
            AddEmplyeeScreen(navController, workerViewModel)
        }
        composable(Routes.ModificarEmpleado.route) { backStackEntry ->
            val workerId = backStackEntry.arguments?.getString("workerId")?.toLongOrNull() ?: 0L
            ModificarEmpleadoScreen(navController, workerId, workerViewModel)
        }

        // Task Management
        composable(Routes.TaskManagement.route) {
            TaskManagementScreen(navController, taskViewModel, workerViewModel)
        }
        composable(Routes.CreateTask.route) {
            CreateTaskScreen(navController, taskViewModel, workerViewModel)
        }
        composable("${Routes.EditTask.route}/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toLongOrNull() ?: 0L
            EditTaskScreen(navController, taskId, taskViewModel, workerViewModel)
        }

        // Reservation Management
        composable(Routes.ReservationManagement.route) {
            GestionReservasScreen(navController, reserveViewModel)
        }
        composable("${Routes.ReservationDetail.route}/{reserveId}") { backStackEntry ->
            val reserveId = backStackEntry.arguments?.getString("reserveId")?.toLongOrNull() ?: 0L
            DetalleReservaScreen(navController, reserveId, reserveViewModel)
        }
    }
}