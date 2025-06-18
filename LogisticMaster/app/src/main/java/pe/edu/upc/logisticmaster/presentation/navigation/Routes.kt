package pe.edu.upc.logisticmaster.presentation.navigation

object Routes {

    val Login = Screen("login")
    val Register = Screen("register")

    val Menu = Screen("menu")
    val PersonalManagement = Screen("personal_management")



    data class Screen(val route: String)
}
