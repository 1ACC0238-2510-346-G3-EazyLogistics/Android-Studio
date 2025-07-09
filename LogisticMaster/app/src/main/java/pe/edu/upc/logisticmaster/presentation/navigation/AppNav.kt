package pe.edu.upc.logisticmaster.presentation.navigation

import AddEmployeeScreen
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
        composable(Routes.Login.route) {
            LoginScreen(navController, authViewModel)
        }
        composable(Routes.Register.route) {
            RegisterScreen(navController, authViewModel)
        }
        composable(Routes.Menu.route) {
            MainMenuScreen(navController, authViewModel)
        }
        composable(Routes.PersonalManagement.route) {
            PersonalManagmentScreen(
                navController    = navController,
                workerViewModel  = workerViewModel,
                authViewModel    = authViewModel
            )
        }
        composable(Routes.AddEmployee.route) {
            AddEmployeeScreen(
                navController,
                workerViewModel
            )
        }
        composable(Routes.ModificarEmpleado.route) {
            ModificarEmpleadoScreen(navController)
        }
        composable(Routes.ReservationManagement.route) {
            GestionReservasScreen(navController, reserveViewModel)
        }
        composable(Routes.ReservationDetail.route) {
            DetalleReservaScreen(navController)
        }
        composable(Routes.Filters.route) {
            FilterScreen(navController)
        }
        composable(Routes.Reports.route) {
            ReportScreen(navController)
        }
    }
}