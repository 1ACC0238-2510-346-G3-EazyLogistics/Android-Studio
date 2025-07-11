package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
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
import pe.edu.upc.logisticmaster.presentation.navigation.Routes
import pe.edu.upc.logisticmaster.presentation.viewmodel.task.TaskViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.task.TaskUiState
import pe.edu.upc.logisticmaster.presentation.view.MenuButton
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerUiState

@Composable
fun TaskManagementScreen(
    navController: NavController,
    taskViewModel: TaskViewModel,
    workerViewModel: WorkerViewModel
) {
    val backgroundColor = Color(0xFF10BEAE)
    val cardColor = Color(0xFFFFFFFF)
    val textColor = Color(0xFF000000)

    val taskUiState by taskViewModel.uiState.collectAsState()
    val workerUiState by workerViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        taskViewModel.loadAllTasks()
        workerViewModel.loadWorkers()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Gestión de\ntareas",
                textAlign = TextAlign.Center,
                color = textColor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(vertical = 8.dp, horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                ActionButton(
                    text = "CREAR TAREA",
                    onClick = { navController.navigate(Routes.CreateTask.route) },
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    text = "ASIGNAR",
                    onClick = { /* Navigate to assign task screen */ },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

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

            // Loading indicator
            if (taskUiState is TaskUiState.Loading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                // Task List
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = cardColor),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Text(
                            "Lista de Tareas:",
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        when (taskUiState) {
                            is TaskUiState.Loaded -> {
                                val tasks = (taskUiState as TaskUiState.Loaded).list
                                if (tasks.isEmpty()) {
                                    Text(
                                        "No hay tareas disponibles",
                                        color = textColor.copy(alpha = 0.6f),
                                        modifier = Modifier.padding(16.dp)
                                    )
                                } else {
                                    LazyColumn(
                                        modifier = Modifier.heightIn(max = 400.dp)
                                    ) {
                                        items(tasks) { task ->
                                            TaskItem(
                                                task = task,
                                                workers = when (workerUiState) {
                                                    is WorkerUiState.Loaded -> (workerUiState as WorkerUiState.Loaded).list
                                                    else -> emptyList()
                                                },
                                                onEdit = {
                                                    navController.navigate("${Routes.EditTask.route}/${task.id}")
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                            else -> {
                                Text(
                                    "No hay tareas disponibles",
                                    color = textColor.copy(alpha = 0.6f),
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            MenuButton("Volver") {
                navController.navigate(Routes.Menu.route) {
                    popUpTo(Routes.TaskManagement.route) { inclusive = true }
                }
            }
        }

        // Footer
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

@Composable
fun TaskItem(
    task: Task,
    workers: List<pe.edu.upc.logisticmaster.domain.model.Worker>,
    onEdit: () -> Unit
) {
    val assignedWorker = workers.find { it.id == task.workerId }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF10BEAE))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.titulo,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = task.descripcion,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp
                )
                Text(
                    text = "Asignado a: ${assignedWorker?.nombre ?: "Sin asignar"}",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 10.sp
                )
            }
            IconButton(onClick = onEdit) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar",
                    tint = Color.White
                )
            }
        }
    }
} 