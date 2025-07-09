package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun ModificarEmpleadoScreen(navController: NavController) {
    val backgroundColor = Color(0xFF10BEAE)
    val accentColor = Color(0xFF10BEAE)

    val empleados = listOf(
        Triple("Andrea Romero", "10101", "Chef"),
        Triple("Arwen Vasquez", "10102", "Secretaria"),
        Triple("Patricia Gutierrez", "10103", "Chef"),
        Triple("Emily Polo", "10104", "Chef"),
        Triple("Miguel Polo", "10105", "Chef"),
        Triple("Walter Polo", "10106", "Chef")
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
                text = "Lista de empleados",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            )

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

            Button(
                onClick = {
                    navController.navigate(Routes.PersonalManagement.route) {
                        popUpTo(Routes.ModificarEmpleado.route) { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Volver", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(12.dp))
            Text("LogisticsMasters", color = Color.White, fontWeight = FontWeight.Bold)
            Text("Potenciando la experiencia hotelera", color = Color.White, fontSize = 12.sp)
        }
    }
}
