package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TempleHindu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.navigation.Routes
import pe.edu.upc.logisticmaster.presentation.viewmodel.reserve.ReserveFormState
import pe.edu.upc.logisticmaster.presentation.viewmodel.reserve.ReserveUiState
import pe.edu.upc.logisticmaster.presentation.viewmodel.reserve.ReserveViewModel

@Composable
fun DetalleReservaScreen(
    navController: NavController,
    reserveViewModel: ReserveViewModel
) {
    val backgroundColor = Color(0xFF10BEAE)
    val fieldColor      = Color.White
    val accentColor     = Color(0xFF10BEAE)
    val textColor       = Color.Black

    // Initialize form (either blank for "new" or loaded)
    LaunchedEffect(Unit) {
        reserveViewModel.updateForm { ReserveFormState() }
    }

    val form by reserveViewModel.formState.collectAsState()
    val uiState by reserveViewModel.uiState.collectAsState(initial = ReserveUiState.Idle)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Detalle de reserva",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier
                .background(fieldColor, RoundedCornerShape(12.dp))
                .padding(vertical = 8.dp, horizontal = 16.dp)
        )

        Spacer(Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = fieldColor),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = form.nombreHuespedes,
                    onValueChange = { reserveViewModel.updateForm { copy(nombreHuespedes = it) } },
                    label = { Text("Nombre huéspedes") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = accentColor,
                        unfocusedContainerColor = accentColor
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = form.habitacion,
                    onValueChange = { reserveViewModel.updateForm { copy(habitacion = it) } },
                    label = { Text("Habitación") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = accentColor,
                        unfocusedContainerColor = accentColor
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = form.horaIngreso,
                    onValueChange = { reserveViewModel.updateForm { copy(horaIngreso = it) } },
                    label = { Text("Hora ingreso") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = accentColor,
                        unfocusedContainerColor = accentColor
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = form.horaSalida,
                    onValueChange = { reserveViewModel.updateForm { copy(horaSalida = it) } },
                    label = { Text("Hora salida") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = accentColor,
                        unfocusedContainerColor = accentColor
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        when (uiState) {
            is ReserveUiState.Loading -> CircularProgressIndicator(color = Color.White)
            is ReserveUiState.Error   -> Text(
                text = (uiState as ReserveUiState.Error).message,
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
            else -> { /* no-op */ }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    // Llama a createReserve en lugar de submitReserve()
                    reserveViewModel.createReserve()
                    navController.popBackStack()
                },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("ACEPTAR", color = Color.Black, fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("CANCELAR", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(Modifier.height(24.dp))

        Icon(
            imageVector = Icons.Default.TempleHindu,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(48.dp)
        )
        Text("LogisticsMasters", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}
