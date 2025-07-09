package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.navigation.Routes
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.AuthViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerUiState
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerViewModel


@Composable
fun PersonalManagementScreen(
    navController: NavController,
    workerViewModel: WorkerViewModel,
    authViewModel: AuthViewModel
) {
    val backgroundColor = Color(0xFF10BEAE)
    val accentColor     = Color(0xFF10BEAE)
    val textColor       = Color.Black

    // Carga inicial de empleados
    LaunchedEffect(Unit) {
        workerViewModel.loadWorkers()
    }

    // Observa el estado de UI
    val uiState by workerViewModel.uiState.collectAsState(initial = WorkerUiState.Idle)
    val empleados = when (uiState) {
        is WorkerUiState.Loaded -> (uiState as WorkerUiState.Loaded).list
        else                    -> emptyList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text      = "Gestión de personal",
            textAlign = TextAlign.Center,
            color     = textColor,
            fontSize  = 28.sp,
            fontWeight= FontWeight.Bold,
            modifier  = Modifier
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(vertical = 12.dp, horizontal = 24.dp)
        )

        Spacer(Modifier.height(16.dp))

        Card(
            modifier  = Modifier.fillMaxWidth(),
            shape     = RoundedCornerShape(16.dp),
            colors    = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier             = Modifier.fillMaxWidth(),
                    horizontalArrangement= Arrangement.SpaceBetween
                ) {
                    Text("NOMBRE", fontWeight = FontWeight.Bold)
                    Text("ID",     fontWeight = FontWeight.Bold)
                    Text("PUESTO", fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(8.dp))
                empleados.forEach { w ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                navController.navigate("${Routes.ModificarEmpleado.route}/${w.id}")
                            },
                        shape  = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = accentColor)
                    ) {
                        Row(
                            modifier              = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(w.nombre, color = Color.White)
                            Text(w.id.toString(), color = Color.White)
                            Text(w.puesto, color = Color.White)
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // Botones de acción con Button estándar
        Row(
            modifier              = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { navController.navigate(Routes.AddEmployee.route) },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("AÑADIR", color = Color.Black, fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = { /* lógica de eliminación */ },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("ELIMINAR", color = Color.Black, fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = { /* navegar a pantalla de tareas */ },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("VER TAREAS", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(Modifier.weight(1f))

        // Logout → vuelve al login limpiando sesión
        Button(
            onClick = {
                authViewModel.logout()
                navController.navigate(Routes.Login.route) {
                    popUpTo(Routes.Menu.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text("Volver", color = textColor, fontWeight = FontWeight.Bold)
        }
    }
}
