package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.navigation.Routes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TempleHindu
import androidx.navigation.NavHostController
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.AuthViewModel

@Composable
fun MainMenuScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val backgroundColor = Color.White
    val buttonColor = Color(0xFF10BEAE)
    val textColor = Color.Black

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        // Ícono de usuario en la parte superior derecha
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Perfil",
            tint = Color.Black,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(48.dp)
        )

        // Cuerpo del menú centrado verticalmente
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Button(
                onClick = { /* Servicios */ },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .width(220.dp)
                    .height(48.dp)
            ) {
                Text("Servicios", color = textColor, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { navController.navigate(Routes.PersonalManagement.route) },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .width(220.dp)
                    .height(48.dp)
            ) {
                Text("Gestion de personal", color = textColor, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { navController.navigate(Routes.ReservationManagement.route) },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .width(220.dp)
                    .height(48.dp)
            ) {
                Text("Administracion de reservas", color = textColor, fontWeight = FontWeight.Bold)
            }
        }

        // Botón Volver abajo centrado
        Button(
            onClick = {
                authViewModel.logout()
                navController.navigate(Routes.Login.route) {
                    popUpTo(Routes.Menu.route) { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(100.dp)
                .height(40.dp)
        ) {
            Text("Volver", color = textColor, fontWeight = FontWeight.Bold)
        }
    }
}
