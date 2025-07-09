package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.R
import pe.edu.upc.logisticmaster.presentation.navigation.Routes
@Composable
fun ReportScreen(
    navController: NavController
) {
    val backgroundColor = Color(0xFF10BEAE)
    val cardColor       = Color.White
    val accentColor     = Color(0xFF10BEAE)

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
                text      = "GENERAR\nINFORME",
                fontSize  = 24.sp,
                fontWeight= FontWeight.Bold,
                color     = Color.Black,
                modifier  = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Spacer(Modifier.height(16.dp))

        // Parámetros
        Card(
            colors = CardDefaults.cardColors(containerColor = accentColor),
            shape  = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                InputLabel("TÍTULO DEL INFORME")
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor   = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                Spacer(Modifier.height(16.dp))
                InputLabel("PARÁMETROS")
                repeat(4) {
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor   = Color.White,
                            unfocusedContainerColor = Color.White
                        )

                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Botones Aceptar / Cancelar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors  = ButtonDefaults.buttonColors(containerColor = cardColor),
                shape   = RoundedCornerShape(8.dp)
            ) {
                Text("CANCELAR", color = accentColor)
            }
            Button(
                onClick = {
                    // Aquí podrías llamar a la lógica de generación de informe
                    navController.navigate(Routes.Reports.route)
                },
                colors  = ButtonDefaults.buttonColors(containerColor = cardColor),
                shape   = RoundedCornerShape(8.dp)
            ) {
                Text("GENERAR", color = accentColor)
            }
        }

        Spacer(Modifier.height(24.dp))

        // Estadísticas (imagen estática)
        Image(
            painter = painterResource(id = R.drawable.logistics_logo),
            contentDescription = "Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
    }
}