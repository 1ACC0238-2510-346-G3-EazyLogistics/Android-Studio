package pe.edu.upc.logisticmaster.presentation.viewmodel.reserve

/**
 * Estado de los campos del formulario de reserva.
 */
data class ReserveFormState(
    val nombreHuespedes: String = "",
    val habitacion: String = "",
    val horaIngreso: String = "",
    val horaSalida: String = ""
) {
    val isValid: Boolean
        get() =
            nombreHuespedes.isNotBlank()
                    && habitacion.isNotBlank()
                    && horaIngreso.isNotBlank()
                    && horaSalida.isNotBlank()
}
