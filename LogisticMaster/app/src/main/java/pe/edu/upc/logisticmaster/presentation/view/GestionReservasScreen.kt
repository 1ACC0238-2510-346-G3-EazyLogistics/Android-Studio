package pe.edu.upc.logisticmaster.presentation.view
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material3.TextField
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.navigation.Routes

data class Reserva(val nombre: String, val numero: String, val habitacion: String, val fecha: String, val estado: String)

@Composable
fun GestionReservasScreen(navController: NavController) {
    val backgroundColor = Color(0xFF10BEAE)
    val cardColor = Color(0xFFFFFFFF)
    val textColor = Color(0xFF000000)

    val reservas = remember {
        mutableStateListOf(
            Reserva("Diego Bertie", "001", "10", "3", "Libre"),
            Reserva("Tony Stark", "002", "104", "5", "Ocupado"),
            Reserva("Alex Broca", "003", "106", "7", "Libre")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Card del título
        Card(
            colors = CardDefaults.cardColors(containerColor = cardColor),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "Administración de reservas",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Card de búsqueda y filtros (solo visual)
        Card(
            colors = CardDefaults.cardColors(containerColor = cardColor),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("BUSCAR RESERVAS", fontWeight = FontWeight.Bold, color = textColor)

                    TextButton(onClick = {
                        navController.navigate(Routes.Filters.route)
                    }) {
                        Text("FILTROS", fontWeight = FontWeight.Bold, color = textColor)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Escriba aquí...") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFE0F7FA),
                        focusedContainerColor = Color(0xFFE0F7FA),
                        unfocusedTextColor = textColor,
                        focusedTextColor = textColor
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

            // Card para botones de acciones
            Card(
                colors = CardDefaults.cardColors(containerColor = cardColor),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Lista de empleados:", color = textColor, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = { /* Agregar */ }) { Text("AGREGAR") }
                        Button(onClick = { /* Modificar */ }) { Text("MODIFICAR") }
                        Button(onClick = { /* Eliminar */ }) { Text("ELIMINAR") }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Lista de empleados
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(reservas) { reserva ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = cardColor),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Nombre: ${reserva.nombre}", color = textColor)
                            Text("N° de Empleado: ${reserva.numero}", color = textColor)
                            Text("N° de Turnos en la semana: ${reserva.fecha}", color = textColor)
                            Text("Estado de Empleado: ${reserva.estado}", color = textColor)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón volver
            Button(
                onClick = {
                    navController.navigate(Routes.Menu.route) {
                        popUpTo(Routes.ReservationManagement.route) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("VOLVER")
            }
        }
    }




