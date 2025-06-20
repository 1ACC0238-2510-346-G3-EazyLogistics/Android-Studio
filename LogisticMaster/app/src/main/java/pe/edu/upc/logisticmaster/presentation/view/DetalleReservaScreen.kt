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

@Composable
fun DetalleReservaScreen(navController: NavController) {
    val backgroundColor = Color(0xFF10BEAE)
    val cardColor = Color(0xFFFFFFFF)
    val textColor = Color(0xFF000000)

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
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "Administración de reservas",
                fontSize = 24.sp,
                color = textColor,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de detalle
        OutlinedTextField(
            value = detalle,
            onValueChange = { detalle = it },
            label = { Text("Detalle de reserva") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botones de acción
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { /* Aceptar lógica */ }) {
                Text("ACEPTAR")
            }
            Button(onClick = { /* Cancelar lógica */ }) {
                Text("CANCELAR")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { /* Estadísticas */ }) {
                Text("ESTADÍSTICAS")
            }
            Button(onClick = {
                navController.navigate(Routes.Reports.route)
            }) {
                Text("GENERAR INFORME")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Imagen de estadística o decorativa
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
            contentDescription = "Estadísticas",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate(Routes.Menu.route) {
                    popUpTo(Routes.ReservationDetail.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("VOLVER A LA PANTALLA PRINCIPAL")
        }
    }
}
