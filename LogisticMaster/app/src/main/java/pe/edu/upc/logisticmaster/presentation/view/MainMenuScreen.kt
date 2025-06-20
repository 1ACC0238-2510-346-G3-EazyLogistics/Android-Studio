package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.navigation.Routes
import pe.edu.upc.logisticmaster.presentation.viewmodel.AppViewModel
import pe.edu.upc.logisticmaster.R

@Composable
fun MainMenuScreen(navController: NavController, appViewModel: AppViewModel) {
    val backgroundColor = Color(0xFF10BEAE)
    val textColor = Color.Black

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // LOGO Y NOMBRE
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logistics_logo),
                contentDescription = "Logo",
                modifier = Modifier.size(150.dp)
            )

            Text(
                text = "LogisticsMasters",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Potenciando la experiencia hotelera",
                color = Color.White,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Logistics Master",
                textAlign = TextAlign.Center,
                color = textColor,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // BOTONES
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            MenuButton("Gestión de personal") {
                navController.navigate(Routes.PersonalManagement.route) {
                    popUpTo(Routes.Menu.route) { inclusive = true }
                }
            }

            MenuButton("Administración de reservas") {
                navController.navigate(Routes.ReservationManagement.route) {
                    popUpTo(Routes.Menu.route)
                }
            }

            MenuButton("Volver") {
                navController.navigate(Routes.Login.route) {
                    popUpTo(Routes.Menu.route) { inclusive = true }
                }
            }
        }
    }
}

@Composable
fun MenuButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
