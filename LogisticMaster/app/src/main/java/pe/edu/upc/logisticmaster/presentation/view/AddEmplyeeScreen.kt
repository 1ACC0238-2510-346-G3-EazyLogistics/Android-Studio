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
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerUiState
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerViewModel
import pe.edu.upc.logisticmaster.presentation.view.InputLabel
import pe.edu.upc.logisticmaster.presentation.view.CustomInputField
import pe.edu.upc.logisticmaster.presentation.view.ActionButton

@Composable
fun AddEmplyeeScreen(
    navController: NavController,
    workerViewModel: WorkerViewModel
) {
    val backgroundColor = Color(0xFF10BEAE)
    val fieldColor = Color.White

    val workerUiState by workerViewModel.uiState.collectAsState()
    val formState by workerViewModel.formState.collectAsState()

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
                text = "Agregar\nEmpleado",
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
            when (workerUiState) {
                is WorkerUiState.Error -> {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.Red.copy(alpha = 0.1f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = (workerUiState as WorkerUiState.Error).message,
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                is WorkerUiState.Success -> {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.Green.copy(alpha = 0.1f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = (workerUiState as WorkerUiState.Success).message,
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
                    InputLabel("NOMBRE")
                    CustomInputField(
                        value = formState.nombre,
                        onValueChange = { workerViewModel.updateNombre(it) }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InputLabel("APELLIDO")
                    CustomInputField(
                        value = formState.apellido,
                        onValueChange = { workerViewModel.updateApellido(it) }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InputLabel("PUESTO")
                    CustomInputField(
                        value = formState.puesto,
                        onValueChange = { workerViewModel.updatePuesto(it) }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InputLabel("ÁREA")
                    CustomInputField(
                        value = formState.area,
                        onValueChange = { workerViewModel.updateArea(it) }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InputLabel("EMAIL")
                    CustomInputField(
                        value = formState.email,
                        onValueChange = { workerViewModel.updateEmail(it) }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InputLabel("TELÉFONO")
                    CustomInputField(
                        value = formState.telefono,
                        onValueChange = { workerViewModel.updateTelefono(it) }
                    )
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
                    text = "AGREGAR",
                    onClick = { 
                        workerViewModel.createWorker()
                        navController.popBackStack()
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
