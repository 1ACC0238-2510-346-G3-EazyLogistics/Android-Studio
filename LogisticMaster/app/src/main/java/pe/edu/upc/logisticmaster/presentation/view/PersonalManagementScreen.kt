package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
    val backgroundColor = Color.White
    val accentColor = Color(0xFF10BEAE)
    val textColor = Color.Black

    LaunchedEffect(Unit) {
        workerViewModel.loadWorkers()
    }

    val uiState by workerViewModel.uiState.collectAsState(initial = WorkerUiState.Idle)
    val empleados = when (uiState) {
        is WorkerUiState.Loaded -> (uiState as WorkerUiState.Loaded).list
        else -> emptyList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Icono de perfil (parte superior derecha)
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Editar perfil",
                modifier = Modifier
                    .size(36.dp)
                    .clickable { navController.navigate(Routes.EditarPerfil) },
                tint = Color.Black
            )
        }

        // Título principal
        Text(
            text = "Gestion de personal",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(accentColor, RoundedCornerShape(12.dp))
                .padding(vertical = 12.dp, horizontal = 24.dp)
        )

        Spacer(Modifier.height(16.dp))

        // Card con botones
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = accentColor),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Lista de empleados:",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
                ) {
                    Button(
                        onClick = { navController.navigate(Routes.AddEmployee.route) },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                    ) {
                        Text("AGREGAR", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }

                    Button(
                        onClick = { /* Acción futura: modificar */ },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                    ) {
                        Text("MODIFICAR", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }

                    Button(
                        onClick = { /* Acción futura: eliminar */ },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                    ) {
                        Text("ELIMINAR", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // Lista de empleados
        empleados.forEach { w ->
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = accentColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(w.nombre, color = Color.White)
                        Text("ID: ${w.id}", color = Color.White)
                        Text(w.puesto, color = Color.White)
                    }
                }
            }
        }

        Spacer(Modifier.weight(1f))

        // Botón Volver
        Button(
            onClick = {
                navController.navigate(Routes.Menu.route) {
                    popUpTo(Routes.PersonalManagement.route) { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = accentColor),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(100.dp)
                .height(40.dp)
        ) {
            Text("Volver", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}
