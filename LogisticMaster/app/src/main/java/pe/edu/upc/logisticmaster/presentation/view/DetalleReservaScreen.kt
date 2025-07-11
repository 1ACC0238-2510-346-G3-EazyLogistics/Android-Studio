package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import pe.edu.upc.logisticmaster.presentation.viewmodel.reserve.ReserveViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.reserve.ReserveUiState
import pe.edu.upc.logisticmaster.presentation.view.MenuButton

@Composable
fun DetalleReservaScreen(
    navController: NavController,
    reserveId: Long,
    reserveViewModel: ReserveViewModel
) {
    val backgroundColor = Color(0xFF10BEAE)
    val cardColor = Color.White

    val reserveUiState by reserveViewModel.uiState.collectAsState()

    LaunchedEffect(reserveId) {
        reserveViewModel.loadReserveById(reserveId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Detalle de\nReserva",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Error/Success message
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
                // Reserve Details
                when (reserveUiState) {
                    is ReserveUiState.LoadedSingle -> {
                        val reserve = (reserveUiState as ReserveUiState.LoadedSingle).reserve
                        ReserveDetailCard(reserve = reserve)
                    }
                    else -> {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = cardColor),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                "No se encontró la reserva",
                                color = Color.Gray,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            MenuButton("VOLVER") {
                navController.popBackStack()
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
fun ReserveDetailCard(reserve: Reserve) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            DetailRow("ID de Reserva:", reserve.id.toString())
            DetailRow("Fecha de Entrada:", reserve.fechaEntrada)
            DetailRow("Fecha de Salida:", reserve.fechaSalida)
            DetailRow("Estado:", reserve.estado)
            DetailRow("Número de Habitación:", reserve.numeroHabitacion.toString())
            DetailRow("Precio:", "$${reserve.precio}")
            DetailRow("ID de Usuario:", reserve.userId.toString())
            DetailRow("ID de Hotel:", reserve.hotelId.toString())
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = value,
            color = Color.Black
        )
    }
} 