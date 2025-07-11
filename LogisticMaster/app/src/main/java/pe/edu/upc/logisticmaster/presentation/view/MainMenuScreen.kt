package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import pe.edu.upc.logisticmaster.presentation.navigation.Routes
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.AuthViewModel
import pe.edu.upc.logisticmaster.presentation.view.MenuButton
import pe.edu.upc.logisticmaster.presentation.view.ActionButton

@Composable
fun MainMenuScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val backgroundColor = Color(0xFF10BEAE)
    val currentUser by authViewModel.currentUser.collectAsState()

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
                text = "Menú\nPrincipal",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(vertical = 8.dp, horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Welcome message
            currentUser?.let { user ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Bienvenido, ${user.usuario}",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Menu Options
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                MenuButton("GESTIÓN DE PERSONAL") {
                    navController.navigate(Routes.PersonalManagement.route)
                }

                MenuButton("GESTIÓN DE TAREAS") {
                    navController.navigate(Routes.TaskManagement.route)
                }

                MenuButton("GESTIÓN DE RESERVAS") {
                    navController.navigate(Routes.ReservationManagement.route)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Logout button
            ActionButton(
                text = "CERRAR SESIÓN",
                onClick = {
                    authViewModel.logout()
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.Menu.route) { inclusive = true }
                    }
                }
            )
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
