package pe.edu.upc.logisticmaster.presentation.view
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.navigation.Routes
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.FlowMainAxisAlignment



data class Reserva(val nombre: String, val numero: String, val habitacion: String, val fecha: String, val estado: String)

@Composable
fun GestionReservasScreen(navController: NavController) {
    val backgroundColor = Color(0xFF10BEAE)
    val cardColor = Color(0xFFFFFFFF)
    val textColor = Color(0xFF000000)
    val accentColor = Color(0xFF10BEAE)

    val reservas = remember {
        mutableStateListOf(
            Reserva("Diego Bertie", "001", "101", "10.55", "Libre"),
            Reserva("Tony Stark", "002", "102", "09:30", "Ocupada"),
            Reserva("Alex Broca", "003", "103", "19:45", "Libre")
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = cardColor),
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Administración de reservas",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        modifier = Modifier.padding(8.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Filtros y búsqueda con fondo turquesa
                    Card(
                        colors = CardDefaults.cardColors(containerColor = accentColor),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "BUSCAR RESERVAS",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                TextButton(onClick = {
                                    navController.navigate(Routes.Filters.route)
                                }) {
                                    Text("FILTROS", fontWeight = FontWeight.Bold, color = Color.White)
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            TextField(
                                value = "",
                                onValueChange = {},
                                placeholder = { Text("Escriba aquí...") },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color(0xFFE0F7FA),
                                    unfocusedContainerColor = Color(0xFFE0F7FA),
                                    focusedTextColor = textColor,
                                    unfocusedTextColor = textColor
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botones
                    Card(
                        colors = CardDefaults.cardColors(containerColor = cardColor),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Lista de Reservas:",
                                fontWeight = FontWeight.Bold,
                                color = textColor
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                mainAxisSpacing = 8.dp,
                                crossAxisSpacing = 8.dp,
                                mainAxisAlignment = FlowMainAxisAlignment.Center
                            ) {
                                Button(
                                    onClick = { /* acción AGREGAR */ },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                                    modifier = Modifier
                                        .height(48.dp)
                                        .defaultMinSize(minWidth = 120.dp)
                                ) {
                                    Text("AGREGAR", color = Color.White, fontSize = 14.sp)
                                }

                                Button(
                                    onClick = {
                                        navController.navigate(Routes.ReservationDetail.route)
                                    },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                                    modifier = Modifier
                                        .height(48.dp)
                                        .defaultMinSize(minWidth = 120.dp)
                                ) {
                                    Text("MODIFICAR", color = Color.White, fontSize = 14.sp)
                                }

                                Button(
                                    onClick = { /* acción ELIMINAR */ },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                                    modifier = Modifier
                                        .height(48.dp)
                                        .defaultMinSize(minWidth = 120.dp)
                                ) {
                                    Text("ELIMINAR", color = Color.White, fontSize = 14.sp)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Lista de reservas con fondo turquesa
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        items(reservas) { reserva ->
                            Card(
                                colors = CardDefaults.cardColors(containerColor = accentColor),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Nombre del huesped: ${reserva.nombre}", color = Color.White)
                                    Text("N° de Habitacion: ${reserva.numero}", color = Color.White)
                                    Text("Hora de ingreso: ${reserva.fecha}", color = Color.White)
                                    Text("Estado de Habitacion: ${reserva.estado}", color = Color.White)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        Button(
            onClick = {
                navController.navigate(Routes.Menu.route) {
                    popUpTo(Routes.ReservationManagement.route) { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("VOLVER", color = Color.Black)
        }
    }
}





