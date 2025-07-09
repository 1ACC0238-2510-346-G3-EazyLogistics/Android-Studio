package pe.edu.upc.logisticmaster.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pe.edu.upc.logisticmaster.presentation.view.DetalleReservaScreen
import pe.edu.upc.logisticmaster.presentation.view.FiltersScreen
import pe.edu.upc.logisticmaster.presentation.view.GestionReservasScreen
import pe.edu.upc.logisticmaster.presentation.view.LoginScreen
import pe.edu.upc.logisticmaster.presentation.view.MainMenuScreen
import pe.edu.upc.logisticmaster.presentation.view.ModificarEmpleadoScreen
import pe.edu.upc.logisticmaster.presentation.view.PersonalManagmentScreen
import pe.edu.upc.logisticmaster.presentation.view.RegisterScreen
import pe.edu.upc.logisticmaster.presentation.view.ReportScreen
import pe.edu.upc.logisticmaster.presentation.viewmodel.AppViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.UserViewModel

@Composable
fun AppNav(
    navController: NavHostController,
    userViewModel: UserViewModel,
    appViewModel: AppViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {

        // --- Auth ---
        composable(Routes.Login.route) {
            LoginScreen(
                navController = navController,
                appViewModel  = appViewModel
            )
        }
        composable(Routes.Register.route) {
            RegisterScreen(
                navController = navController,
                appViewModel  = appViewModel
            )
        }

        // --- Main menu ---
        composable(Routes.Menu.route) {
            MainMenuScreen(
                navController = navController,
                appViewModel  = appViewModel
            )
        }

        // --- Workers ---
        composable(Routes.PersonalManagement.route) {
            PersonalManagmentScreen(
                navController    = navController,
                userViewModel    = userViewModel,
                appViewModel     = appViewModel
            )
        }
        composable(Routes.AddEmployee.route) {
            AddEmployeeScreen(
                navController    = navController,
                userViewModel    = userViewModel
            )
        }
        composable(Routes.ModificarEmpleado.route) {
            ModificarEmpleadoScreen(
                navController    = navController,
                userViewModel    = userViewModel
            )
        }

        // --- Reservations ---
        composable(Routes.ReservationManagement.route) {
            GestionReservasScreen(
                navController    = navController,
                appViewModel     = appViewModel
            )
        }
        composable(Routes.ReservationDetail.route) {
            DetalleReservaScreen(
                navController    = navController,
                appViewModel     = appViewModel
            )
        }
        composable(Routes.Filters.route) {
            FiltersScreen(
                navController    = navController
            )
        }
        composable(Routes.Reports.route) {
            ReportScreen(
                navController    = navController
            )
        }
    }
}
