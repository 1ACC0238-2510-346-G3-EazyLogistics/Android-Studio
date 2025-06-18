package pe.edu.upc.logisticmaster.presentation.navigation

import RegisterScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pe.edu.upc.logisticmaster.presentation.view.LoginScreen
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
    }

}