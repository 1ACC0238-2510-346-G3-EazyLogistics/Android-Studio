package pe.edu.upc.logisticmaster.presentation.navigation

object Routes {

    val Login = Screen("login")
    val Register = Screen("register")
    val Reserve = Screen("reserve")




    data class Screen(val route: String)
}
