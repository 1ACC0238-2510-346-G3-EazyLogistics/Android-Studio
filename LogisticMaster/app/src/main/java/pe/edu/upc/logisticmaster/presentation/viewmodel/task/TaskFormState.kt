package pe.edu.upc.logisticmaster.presentation.viewmodel.task

/**
 * Estado de los campos del formulario de tarea.
 */
data class TaskFormState(
    val titulo: String = "",
    val descripcion: String = "",
    val workerId: Long? = null
) {
    /** Sólo válido cuando todos los campos están completos */
    val isValid: Boolean
        get() =
            titulo.isNotBlank() &&
                    descripcion.isNotBlank() &&
                    (workerId ?: 0L) > 0L
}
