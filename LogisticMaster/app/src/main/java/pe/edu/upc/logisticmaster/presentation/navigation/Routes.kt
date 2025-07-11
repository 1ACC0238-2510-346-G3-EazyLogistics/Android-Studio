package pe.edu.upc.logisticmaster.presentation.navigation

object Routes {

    // Authentication
    val Login = Screen("login")
    val Register = Screen("register")

    // Main Dashboard
    val Menu = Screen("menu")

    // Worker Management
    val PersonalManagement = Screen("personal_management")
    val AddEmployee = Screen("add_employee")
    val ModificarEmpleado = Screen(route = "modificarEmpleado")

    // Task Management
    val TaskManagement = Screen("task_management")
    val CreateTask = Screen("create_task")
    val EditTask = Screen("edit_task")

    // Reservation Management
    val ReservationManagement = Screen("reservation_management")
    val ReservationDetail = Screen("reservation_detail")

    data class Screen(val route: String)
}
