package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TempleHindu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.domain.model.Task
import pe.edu.upc.logisticmaster.presentation.viewmodel.task.TaskViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.task.TaskUiState
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerUiState
import pe.edu.upc.logisticmaster.presentation.viewmodel.task.TaskFormState
import pe.edu.upc.logisticmaster.presentation.view.InputLabel
import pe.edu.upc.logisticmaster.presentation.view.CustomInputField
import pe.edu.upc.logisticmaster.presentation.view.ActionButton

@Composable
fun EditTaskScreen(
    navController: NavController,
    taskId: Long,
    taskViewModel: TaskViewModel,
    workerViewModel: WorkerViewModel
) {
    val backgroundColor = Color(0xFF10BEAE)
    val fieldColor = Color.White

    val taskUiState by taskViewModel.uiState.collectAsState()
    val workerUiState by workerViewModel.uiState.collectAsState()
    val formState by taskViewModel.formState.collectAsState()

    LaunchedEffect(Unit) {
        workerViewModel.loadWorkers()
        // Load task by ID and populate form
        if (taskId > 0) {
            taskViewModel.loadTaskById(taskId)
        }
    }

    // Navigate back on success
    LaunchedEffect(taskUiState) {
        if (taskUiState is TaskUiState.Success) {
            navController.popBackStack()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Editar\ntarea",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Error/Success message
            when (taskUiState) {
                is TaskUiState.Error -> {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.Red.copy(alpha = 0.1f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = (taskUiState as TaskUiState.Error).message,
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                is TaskUiState.Success -> {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.Green.copy(alpha = 0.1f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = (taskUiState as TaskUiState.Success).message,
                            color = Color.Green,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                else -> {}
            }

            // Formulario
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = fieldColor),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    InputLabel("TÍTULO DE LA TAREA")
                    CustomInputField(
                        value = formState.titulo, 
                        onValueChange = { taskViewModel.updateTitulo(it) }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InputLabel("DESCRIPCIÓN")
                    CustomInputField(
                        value = formState.descripcion,
                        onValueChange = { taskViewModel.updateDescripcion(it) },
                        singleLine = false
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InputLabel("ASIGNAR A EMPLEADO")
                    when (workerUiState) {
                        is WorkerUiState.Loaded -> {
                            val workers = (workerUiState as WorkerUiState.Loaded).list
                            
                            // No assignment option
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = formState.workerId == null,
                                    onClick = { taskViewModel.updateWorkerId(null) }
                                )
                                Text(
                                    text = "Sin asignar",
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                            
                            if (workers.isNotEmpty()) {
                                workers.forEach { worker ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        RadioButton(
                                            selected = formState.workerId == worker.id,
                                            onClick = { taskViewModel.updateWorkerId(worker.id) }
                                        )
                                        Text(
                                            text = "${worker.nombre} ${worker.apellido}",
                                            modifier = Modifier.padding(start = 8.dp)
                                        )
                                    }
                                }
                            } else {
                                Text(
                                    text = "No hay empleados disponibles",
                                    color = Color.Gray,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                        is WorkerUiState.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                        else -> {
                            Text(
                                text = "No hay empleados disponibles",
                                color = Color.Gray,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Botones
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                ActionButton(
                    text = "CANCELAR",
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    text = "ACTUALIZAR",
                    onClick = { 
                        taskViewModel.updateTask(taskId)
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Logo pie de página
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.TempleHindu,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(48.dp)
            )
            Text("LogisticsMasters", color = Color.White, fontWeight = FontWeight.Bold)
            Text("Potenciando la experiencia hotelera", color = Color.White, fontSize = 12.sp)
        }
    }
} 