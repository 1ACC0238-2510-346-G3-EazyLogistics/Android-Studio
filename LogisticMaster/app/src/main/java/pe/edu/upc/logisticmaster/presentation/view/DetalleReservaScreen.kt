package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.navigation.Routes
import androidx.compose.material3.OutlinedTextFieldDefaults


@Composable
fun DetalleReservaScreen(navController: NavController) {
    val backgroundColor = Color(0xFF10BEAE)
    val cardColor = Color.White
    val textColor = Color.Black

    var detalle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Título
        Card(
            colors = CardDefaults.cardColors(containerColor = cardColor),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Administración de reservas",
                fontSize = 24.sp,
                color = textColor,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Contenedor blanco para campos y acciones
        Card(
            colors = CardDefaults.cardColors(containerColor = cardColor),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Campo de detalle
                OutlinedTextField(
                    value = detalle,
                    onValueChange = { detalle = it },
                    label = { Text("Detalle de reserva") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = cardColor,
                        unfocusedContainerColor = cardColor,
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = backgroundColor
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Botones de acción
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { /* Aceptar lógica */ },
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text("ACEPTAR", color = Color.Black)
                    }

                    Button(
                        onClick = { /* Cancelar lógica */ },
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text("CANCELAR", color = Color.Black)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { /* Estadísticas */ },
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text("ESTADÍSTICAS", color = Color.Black)
                    }

                    Button(
                        onClick = {
                            navController.navigate(Routes.Reports.route)
                        },
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text("GENERAR INFORME", color = Color.Black)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Imagen decorativa
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                    contentDescription = "Estadísticas",
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón volver
        Button(
            onClick = {
                navController.navigate(Routes.Menu.route) {
                    popUpTo(Routes.ReservationDetail.route) { inclusive = true }
                }
            },
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("VOLVER A LA PANTALLA PRINCIPAL", color = Color.Black)
        }
    }
}

