package pe.edu.upc.logisticmaster.presentation.navigation

object Routes {

    val EditarPerfil = "editar_perfil"
    val Login = Screen("login")
    val Register = Screen("register")

    val Menu = Screen("menu")
    val PersonalManagement = Screen("personal_management")

    val Filters = Screen("filters")
    val Reports = Screen("reports")
    val ReservationDetail = Screen("reservation_detail")
    val ReservationManagement = Screen("reservation_management")
    val AddEmployee = Screen("add_employee")
    val ModificarEmpleado = Screen(route = "modificarEmpleado")
    val Report = object {
        val route = "report"
    }

    data class Screen(val route: String)
}
