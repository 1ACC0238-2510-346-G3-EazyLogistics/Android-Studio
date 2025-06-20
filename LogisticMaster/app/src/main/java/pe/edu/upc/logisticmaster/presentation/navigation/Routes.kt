package pe.edu.upc.logisticmaster.presentation.navigation

object Routes {

    val Login = Screen("login")
    val Register = Screen("register")

    val Menu = Screen("menu")
    val PersonalManagement = Screen("personal_management")

    val Filters = Screen("filters")
    val Reports = Screen("reports")
    val ReservationDetail = Screen("reservation_detail")
    val ReservationManagement = Screen("reservation_management")
 val  AddEmployee = Screen("add_employee")
    data class Screen(val route: String)
}
