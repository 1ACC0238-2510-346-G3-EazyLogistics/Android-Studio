package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.navigation.Routes

@Composable
fun FiltersScreen(navController: NavController) {
    val backgroundColor = Color(0xFF10BEAE)
    val cardColor = Color(0xFFFFFFFF)
    val textColor = Color(0xFF000000)

    var nombre by remember { mutableStateOf("") }
    var habitacion by remember { mutableStateOf("") }
    var fechas by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = cardColor),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("FILTROS DE BUSQUEDA", fontSize = 24.sp, color = textColor)

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre de reserva") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = habitacion,
                    onValueChange = { habitacion = it },
                    label = { Text("Habitación") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = fechas,
                    onValueChange = { fechas = it },
                    label = { Text("Fechas") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = estado,
                    onValueChange = { estado = it },
                    label = { Text("Estado de reserva") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {
                        navController.popBackStack() // vuelve atrás sin aplicar
                    }) {
                        Text("CANCELAR")
                    }

                    Button(onClick = {
                        navController.navigate(Routes.ReservationManagement.route) {
                            popUpTo(Routes.Filters.route) { inclusive = true }
                        }
                    }) {
                        Text("APLICAR")
                    }
                }
            }
        }
    }
}
