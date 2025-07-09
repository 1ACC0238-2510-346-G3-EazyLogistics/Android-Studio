package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.navigation.Routes
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerUiState
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerViewModel

@Composable
fun ModificarEmpleadoScreen(
    navController: NavController,
    workerViewModel: WorkerViewModel
) {
    val backgroundColor = Color(0xFF10BEAE)
    val accentColor     = Color(0xFF10BEAE)
    val textColor       = Color.Black

    // Disparar la carga de empleados al entrar
    LaunchedEffect(Unit) {
        workerViewModel.loadWorkers()
    }

    // Observar el estado de UI (con valor inicial Idle)
    val uiState by workerViewModel.uiState.collectAsState(initial = WorkerUiState.Idle)

    // Extraer la lista cuando esté cargada
    val empleados = when (uiState) {
        is WorkerUiState.Loaded -> (uiState as WorkerUiState.Loaded).list
        else                     -> emptyList()
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
                text      = "Modificar\nEmpleado",
                textAlign = TextAlign.Center,
                color     = textColor,
                fontSize  = 28.sp,
                fontWeight= FontWeight.Bold,
                modifier  = Modifier
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(vertical = 8.dp, horizontal = 24.dp)
            )

            Spacer(Modifier.height(16.dp))

            when (uiState) {
                is WorkerUiState.Loading -> {
                    CircularProgressIndicator(color = Color.White)
                }
                is WorkerUiState.Error -> {
                    Text(
                        text = (uiState as WorkerUiState.Error).message,
                        color = Color.Red
                    )
                }
                is WorkerUiState.Loaded -> {
                    Card(
                        modifier   = Modifier.fillMaxWidth(),
                        shape      = RoundedCornerShape(16.dp),
                        colors     = CardDefaults.cardColors(containerColor = Color.White),
                        elevation  = CardDefaults.cardElevation(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("NOMBRE", fontWeight = FontWeight.Bold)
                                Text("ID",     fontWeight = FontWeight.Bold)
                                Text("PUESTO", fontWeight = FontWeight.Bold)
                            }
                            Spacer(Modifier.height(8.dp))
                            empleados.forEach { worker ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                        .clickable {
                                            // Aquí podrías navegar a un formulario de edición,
                                            // pasando worker.id como parámetro
                                            navController.navigate("${Routes.ModificarEmpleado.route}/${worker.id}")
                                        },
                                    shape  = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = accentColor)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 12.dp, vertical = 8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(worker.nombre, color = Color.White)
                                        Text(worker.id.toString(), color = Color.White)
                                        Text(worker.puesto, color = Color.White)
                                    }
                                }
                            }
                        }
                    }
                }
                else -> {}
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.popBackStack()
                },
                colors   = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape    = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Volver", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(12.dp))
            Text("LogisticsMasters", color = Color.White, fontWeight = FontWeight.Bold)
            Text("Potenciando la experiencia hotelera", color = Color.White, fontSize = 12.sp)
        }
    }
}
