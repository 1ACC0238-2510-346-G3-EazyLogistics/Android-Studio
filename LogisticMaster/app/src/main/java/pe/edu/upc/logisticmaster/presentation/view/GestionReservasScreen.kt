package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TempleHindu
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
import pe.edu.upc.logisticmaster.domain.model.Reserve
import pe.edu.upc.logisticmaster.presentation.navigation.Routes
import pe.edu.upc.logisticmaster.presentation.viewmodel.reserve.ReserveViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.reserve.ReserveUiState
import pe.edu.upc.logisticmaster.presentation.view.MenuButton

@Composable
fun GestionReservasScreen(
    navController: NavController,
    reserveViewModel: ReserveViewModel
) {
    val backgroundColor = Color(0xFF10BEAE)
    val cardColor = Color(0xFFFFFFFF)
    val textColor = Color(0xFF000000)

    val reserveUiState by reserveViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        reserveViewModel.loadAllReserves()
    }

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
                text = "Gestión de\nReservas",
                textAlign = TextAlign.Center,
                color = textColor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(vertical = 8.dp, horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Error message
            when (reserveUiState) {
                is ReserveUiState.Error -> {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.Red.copy(alpha = 0.1f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = (reserveUiState as ReserveUiState.Error).message,
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                else -> {}
            }

            // Loading indicator
            if (reserveUiState is ReserveUiState.Loading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                // Reserve List
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = cardColor),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Text(
                            "Lista de Reservas:",
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        when (reserveUiState) {
                            is ReserveUiState.Loaded -> {
                                val reserves = (reserveUiState as ReserveUiState.Loaded).reserves
                                if (reserves.isEmpty()) {
                                    Text(
                                        "No hay reservas disponibles",
                                        color = textColor.copy(alpha = 0.6f),
                                        modifier = Modifier.padding(16.dp)
                                    )
                                } else {
                                    LazyColumn(
                                        modifier = Modifier.heightIn(max = 400.dp)
                                    ) {
                                        items(reserves) { reserve ->
                                            ReserveItem(
                                                reserve = reserve,
                                                onClick = {
                                                    navController.navigate("${Routes.ReservationDetail.route}/${reserve.id}")
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                            else -> {
                                Text(
                                    "No hay reservas disponibles",
                                    color = textColor.copy(alpha = 0.6f),
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            MenuButton("Volver") {
                navController.navigate(Routes.Menu.route) {
                    popUpTo(Routes.ReservationManagement.route) { inclusive = true }
                }
            }
        }

        // Footer
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

@Composable
fun ReserveItem(
    reserve: Reserve,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF10BEAE))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Reserva #${reserve.id}",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Habitación: ${reserve.numeroHabitacion}",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp
            )
            Text(
                text = "Estado: ${reserve.estado}",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 10.sp
            )
            Text(
                text = "Precio: $${reserve.precio}",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 10.sp
            )
        }
    }
}
