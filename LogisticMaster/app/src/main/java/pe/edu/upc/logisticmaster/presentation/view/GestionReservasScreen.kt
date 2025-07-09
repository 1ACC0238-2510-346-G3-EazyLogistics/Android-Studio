package pe.edu.upc.logisticmaster.presentation.view
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import pe.edu.upc.logisticmaster.presentation.viewmodel.reserve.ReserveUiState
import pe.edu.upc.logisticmaster.presentation.viewmodel.reserve.ReserveViewModel


@Composable
fun GestionReservasScreen(
    navController: NavController,
    reserveViewModel: ReserveViewModel
) {
    val backgroundColor = Color(0xFF10BEAE)
    val cardColor       = Color.White
    val accentColor     = Color(0xFF10BEAE)
    val textColor       = Color.Black

    // Buscar texto
    var query by remember { mutableStateOf("") }

    // Carga inicial de reservas
    LaunchedEffect(Unit) {
        reserveViewModel.loadReserves()
    }

    // Observa el estado UI
    val uiState by reserveViewModel.uiState.collectAsState(initial = ReserveUiState.Idle)
    val reservas = when (uiState) {
        is ReserveUiState.Loaded  -> (uiState as ReserveUiState.Loaded).list
        else                      -> emptyList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Card(
            colors = CardDefaults.cardColors(containerColor = cardColor),
            shape  = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text      = "Administración de reservas",
                fontSize  = 24.sp,
                fontWeight= FontWeight.Bold,
                color     = textColor,
                modifier  = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Spacer(Modifier.height(16.dp))

        // Barra de búsqueda y filtros
        Card(
            colors = CardDefaults.cardColors(containerColor = accentColor),
            shape  = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment   = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = { Text("Buscar huéspedes...") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor   = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(8.dp))
                Button(
                    onClick = { /* desplegar filtros */ },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = cardColor)
                ) {
                    Text("Filtros", color = accentColor)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Botones de acción
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    // navegar a detalle en modo “crear” (id = null)
                    navController.navigate(Routes.ReservationDetail.route.replace("{id}", "new"))
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                modifier = Modifier.weight(1f)
            ) {
                Text("Agregar", color = Color.White)
            }
            Button(
                onClick = {
                    // aquí quizás abrir detalle del elemento seleccionado
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                modifier = Modifier.weight(1f)
            ) {
                Text("Modificar", color = Color.White)
            }
            Button(
                onClick = {
                    // borrar todas o la seleccionada
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                modifier = Modifier.weight(1f)
            ) {
                Text("Eliminar", color = Color.White)
            }
        }

        Spacer(Modifier.height(16.dp))

        // Lista filtrada
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(reservas.filter { it.nombreHuespedes.contains(query, ignoreCase = true) }) { reserve ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = accentColor),
                    shape  = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            navController.navigate(
                                Routes.ReservationDetail.route.replace("{id}", reserve.id.toString())
                            )
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(reserve.nombreHuespedes, color = Color.White, fontWeight = FontWeight.Bold)
                            Text("Hab.: ${reserve.habitacion}", color = Color.White)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(reserve.horaIngreso, color = Color.White)
                            Text(reserve.horaSalida, color = Color.White)
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        // Ver informe
        Button(
            onClick = { navController.navigate(Routes.Reports.route) },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = cardColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Ver Informe", color = accentColor)
        }

        Spacer(Modifier.height(16.dp))

        // Volver
        Button(
            onClick = {
                navController.navigate(Routes.Menu.route) {
                    popUpTo(Routes.ReservationManagement.route) { inclusive = true }
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = cardColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Volver", color = accentColor)
        }
    }
}
