package pe.edu.upc.logisticmaster.presentation.view

import ActionButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TempleHindu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.navigation.Routes
import pe.edu.upc.logisticmaster.presentation.viewmodel.AppViewModel

@Composable
fun PersonalManagementScreen(navController: NavController, appViewModel: AppViewModel) {
    val backgroundColor = Color(0xFF10BEAE)
    val textColor = Color.Black
    val accentColor = Color(0xFF10BEAE)

    val empleados = listOf(
        Triple("Andrea Romero", "10101", "Chef"),
        Triple("Arwen Vasquez", "10102", "Secretaria"),
        Triple("Patricia Gutierrez", "10103", "Chef"),
        Triple("Emily Polo", "10104", "Chef"),
        Triple("Miguel Polo", "10105", "Chef"),
        Triple("Walter Polo", "10106", "Chef"),
    )

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
                text = "Gestión de\npersonal",
                textAlign = TextAlign.Center,
                color = textColor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(vertical = 8.dp, horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Lista de empleados:", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                ActionButton("AÑADIR") {
                    navController.navigate(Routes.AddEmployee.route)
                }
                ActionButton("MODIFICAR") {
                    navController.navigate(Routes.ModificarEmpleado.route)
                }
                ActionButton("ELIMINAR") {
                    // Implementar lógica si se desea
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp)
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
                        Text("ID", fontWeight = FontWeight.Bold)
                        Text("PUESTO", fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    empleados.forEach { (nombre, id, puesto) ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = accentColor)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(nombre, color = Color.White)
                                Text(id, color = Color.White)
                                Text(puesto, color = Color.White)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            MenuButton("Asignar tareas") {
                // Acción si se desea
            }

            Spacer(modifier = Modifier.height(8.dp))

            MenuButton("Volver") {
                navController.navigate(Routes.Menu.route) {
                    popUpTo(Routes.PersonalManagement.route) { inclusive = true }
                }
            }
        }

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
