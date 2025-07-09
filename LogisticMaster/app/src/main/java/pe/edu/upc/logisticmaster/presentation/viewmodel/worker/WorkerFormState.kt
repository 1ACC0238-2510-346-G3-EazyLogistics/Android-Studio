package pe.edu.upc.logisticmaster.presentation.viewmodel.worker

/**
 * Estado de los campos del formulario de trabajador.
 */
data class WorkerFormState(
    val nombre: String = "",
    val apellido: String = "",
    val email: String = "",
    val telefono: String = "",
    val puesto: String = "",
    val area: String = ""
) {
    /** Sólo válido si todos los campos están completos */
    val isValid: Boolean
        get() =
            nombre.isNotBlank() &&
                    apellido.isNotBlank() &&
                    email.isNotBlank() &&
                    telefono.isNotBlank() &&
                    puesto.isNotBlank() &&
                    area.isNotBlank()
}
