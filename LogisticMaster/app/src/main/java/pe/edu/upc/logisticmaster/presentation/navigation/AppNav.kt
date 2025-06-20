package pe.edu.upc.logisticmaster.presentation.navigation

import RegisterScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pe.edu.upc.logisticmaster.presentation.view.DetalleReservaScreen
import pe.edu.upc.logisticmaster.presentation.view.FiltersScreen
import pe.edu.upc.logisticmaster.presentation.view.GestionReservasScreen
import pe.edu.upc.logisticmaster.presentation.view.LoginScreen
import pe.edu.upc.logisticmaster.presentation.view.MainMenuScreen
import pe.edu.upc.logisticmaster.presentation.view.PersonalManagementScreen
import pe.edu.upc.logisticmaster.presentation.view.ReportScreen
import pe.edu.upc.logisticmaster.presentation.viewmodel.AppViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.UserViewModel

@Composable
fun AppNav(navController: NavHostController,    userViewModel: UserViewModel, appViewModel: AppViewModel) {

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {

            composable(Routes.Login.route) {
                LoginScreen(navController = navController, appViewModel = appViewModel)
            }
        composable(Routes.Register.route) {
            RegisterScreen(navController = navController, appViewModel = appViewModel)
        }
        composable(Routes.Menu.route) {
            MainMenuScreen(navController = navController, appViewModel = appViewModel)
        }
        composable(Routes.PersonalManagement.route) {
            PersonalManagementScreen(navController = navController, appViewModel = appViewModel)
        }
        composable(Routes.Filters.route) {
            FiltersScreen(navController = navController)
        }
        composable(Routes.Reports.route) {
            ReportScreen()
        }
        composable(Routes.ReservationDetail.route) {
            DetalleReservaScreen()
        }
        composable(Routes.ReservationManagement.route) {
            GestionReservasScreen(navController = navController)
        }
    }
}