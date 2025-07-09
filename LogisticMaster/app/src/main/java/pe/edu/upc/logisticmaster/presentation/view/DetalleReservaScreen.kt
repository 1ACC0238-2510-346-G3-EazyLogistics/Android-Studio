package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TempleHindu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.navigation.Routes


@Composable
fun FilterScreen(
    navController: NavController
) {
    val backgroundColor = Color(0xFF10BEAE)
    val cardColor       = Color.White
    val accentColor     = Color(0xFF10BEAE)
    val textColor       = Color.Black

    var nombreHuesped by remember { mutableStateOf("") }
    var habitacion by remember { mutableStateOf("") }
    var fechaReserva by remember { mutableStateOf("") }
    var horaIngreso by remember { mutableStateOf("") }
    var horaSalida by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors   = CardDefaults.cardColors(containerColor = cardColor),
            shape    = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text      = "FILTROS DE BÚSQUEDA",
                fontSize  = 20.sp,
                fontWeight= FontWeight.Bold,
                color     = textColor,
                modifier  = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Spacer(Modifier.height(16.dp))

        Card(
            colors   = CardDefaults.cardColors(containerColor = cardColor),
            shape    = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                InputLabel("NOMBRE DE HUESPED")
                OutlinedTextField(
                    value = nombreHuesped,
                    onValueChange = { nombreHuesped = it },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor   = accentColor,
                        unfocusedContainerColor = accentColor,
                        focusedTextColor        = textColor,
                        unfocusedTextColor      = textColor,
                        focusedBorderColor      = Color.Transparent,
                        unfocusedBorderColor    = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))
                InputLabel("HABITACIÓN")
                OutlinedTextField(
                    value = habitacion,
                    onValueChange = { habitacion = it },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor   = accentColor,
                        unfocusedContainerColor = accentColor,
                        focusedTextColor        = textColor,
                        unfocusedTextColor      = textColor,
                        focusedBorderColor      = Color.Transparent,
                        unfocusedBorderColor    = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))
                InputLabel("FECHA DE RESERVA")
                OutlinedTextField(
                    value = fechaReserva,
                    onValueChange = { fechaReserva = it },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor   = accentColor,
                        unfocusedContainerColor = accentColor,
                        focusedTextColor        = textColor,
                        unfocusedTextColor      = textColor,
                        focusedBorderColor      = Color.Transparent,
                        unfocusedBorderColor    = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))
                InputLabel("HORA DE INGRESO")
                OutlinedTextField(
                    value = horaIngreso,
                    onValueChange = { horaIngreso = it },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor   = accentColor,
                        unfocusedContainerColor = accentColor,
                        focusedTextColor        = textColor,
                        unfocusedTextColor      = textColor,
                        focusedBorderColor      = Color.Transparent,
                        unfocusedBorderColor    = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))
                InputLabel("HORA DE SALIDA")
                OutlinedTextField(
                    value = horaSalida,
                    onValueChange = { horaSalida = it },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor   = accentColor,
                        unfocusedContainerColor = accentColor,
                        focusedTextColor        = textColor,
                        unfocusedTextColor      = textColor,
                        focusedBorderColor      = Color.Transparent,
                        unfocusedBorderColor    = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ActionButton(text = "CANCELAR") {
                        navController.popBackStack()
                    }
                    ActionButton(text = "BUSCAR") {
                        navController.navigate(Routes.ReservationManagement.route)
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Icon(
            imageVector   = Icons.Default.TempleHindu,
            contentDescription = null,
            tint          = Color.White,
            modifier      = Modifier.size(48.dp)
        )
        Text(
            "LogisticsMasters",
            color     = Color.White,
            fontSize  = 16.sp,
            fontWeight= FontWeight.Bold
        )
    }
}
