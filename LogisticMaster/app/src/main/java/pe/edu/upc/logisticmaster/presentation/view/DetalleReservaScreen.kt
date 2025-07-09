package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import pe.edu.upc.logisticmaster.R



@Composable
fun DetalleReservaScreen(navController: NavController) {
    val backgroundColor = Color(0xFF10BEAE)
    val cardColor = Color.White
    val accentColor = Color(0xFF10BEAE)
    val textColor = Color.Black

    var detalle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Card título
        Card(
            colors = CardDefaults.cardColors(containerColor = cardColor),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "Administración de reservas",
                fontSize = 24.sp,
                color = textColor,
                modifier = Modifier.padding(16.dp),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Card campos y botones
        Card(
            colors = CardDefaults.cardColors(containerColor = cardColor),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "DETALLE DE RESERVA",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = detalle,
                    onValueChange = { detalle = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = accentColor,
                        unfocusedBorderColor = Color.Gray,
                        focusedContainerColor = cardColor,
                        unfocusedContainerColor = cardColor
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { /* Acción aceptar */ },
                        colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("ACEPTAR", color = Color.White)
                    }

                    Button(
                        onClick = { /* Acción cancelar */ },
                        colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("CANCELAR", color = Color.White)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón ESTADÍSTICAS
        Button(
            onClick = { /* Acción estadísticas */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(50),
        ) {
            Text("ESTADÍSTICAS", color = textColor)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Imagen personalizada
        Image(
            painter = painterResource(id = R.drawable.estadisticas), // ← debes ponerla en `res/drawable`
            contentDescription = "Estadísticas",
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón GENERAR INFORME
        Button(
            onClick = { navController.navigate(Routes.Reports.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(50)
        ) {
            Text("GENERAR INFORME", color = textColor)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate(Routes.Menu.route) {
                    popUpTo(Routes.ReservationDetail.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(30)
        ) {
            Text("VOLVER A LA PANTALLA PRINCIPAL", color = textColor)
        }
        Spacer(modifier = Modifier.height(24.dp))
        // Imagen (logo)
        Image(
            painter = painterResource(id = R.drawable.logistics_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentScale = ContentScale.Fit
        )
    }
}


