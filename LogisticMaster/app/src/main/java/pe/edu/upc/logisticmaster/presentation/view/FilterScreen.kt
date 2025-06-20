package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.navigation.Routes

@Composable
fun FiltersScreen(navController: NavController) {
    val backgroundColor = Color(0xFF10BEAE)
    val cardColor = Color.White
    val accentColor = Color(0xFF10BEAE)
    val textColor = Color.Black

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                colors = CardDefaults.cardColors(containerColor = cardColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = "FILTROS DE BUSQUEDA",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            // Card blanco con campos y botones
            Card(
                colors = CardDefaults.cardColors(containerColor = cardColor),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    listOf(
                        "Nombre de reserva",
                        "Habitación",
                        "Fechas",
                        "Estado de reserva"
                    ).forEach { placeholder ->
                        TextField(
                            value = "",
                            onValueChange = {},
                            placeholder = { Text(placeholder) },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color(0xFFF1F1F1),
                                unfocusedContainerColor = Color(0xFFF1F1F1),
                                focusedTextColor = textColor,
                                unfocusedTextColor = textColor
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = { navController.popBackStack() },
                            colors = ButtonDefaults.buttonColors(containerColor = accentColor)
                        ) {
                            Text("CANCELAR", color = Color.White)
                        }

                        Button(
                            onClick = { navController.navigate(Routes.ReservationManagement.route) },
                            colors = ButtonDefaults.buttonColors(containerColor = accentColor)
                        ) {
                            Text("APLICAR", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}


