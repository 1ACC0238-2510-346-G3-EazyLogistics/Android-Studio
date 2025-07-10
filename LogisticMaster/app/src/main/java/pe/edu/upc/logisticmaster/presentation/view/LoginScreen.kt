package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.navigation.Routes
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.AuthUiState
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.AuthViewModel
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import pe.edu.upc.logisticmaster.R


@Composable
fun LoginView(
    navController: NavController,
    viewModel: AuthViewModel
) {
    val backgroundColor = Color.White
    val accentColor = Color(0xFF10BEAE)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val authState by viewModel.state.collectAsState()

    // Cuando el estado sea Success, navegamos a la pantalla de menú
    if (authState is AuthUiState.Success) {
        LaunchedEffect(Unit) {
            navController.navigate(Routes.Menu.route) {
                // Evita volver a Login
                popUpTo(Routes.Login.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // — Logo y título (igual que antes) —
        Image(
            painter = painterResource(id = R.drawable.iconologistics),
            contentDescription = "Logo",
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 24.dp)
        )
        Text(
            text = "Logistics Master",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(24.dp))

        // — Email —
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("EMAIL") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = accentColor,
                unfocusedBorderColor = accentColor,
                focusedLabelColor = accentColor,
                unfocusedLabelColor = accentColor
            )
        )

        Spacer(Modifier.height(12.dp))

        // — Contraseña —
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("CONTRASEÑA") },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                IconButton(
                    onClick = { passwordVisible = !passwordVisible }  // <-- aquí
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                        tint = accentColor
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = accentColor,
                unfocusedBorderColor = accentColor,
                focusedLabelColor = accentColor,
                unfocusedLabelColor = accentColor
            )
        )


        Spacer(Modifier.height(16.dp))

        // — Indicadores de estado —
        when (authState) {
            is AuthUiState.Loading -> CircularProgressIndicator(color = accentColor)
            is AuthUiState.Error   -> Text(
                text = (authState as AuthUiState.Error).message,
                color = Color.Red
            )
            else -> Unit
        }

        Spacer(Modifier.height(16.dp))

        // — Botón único de LOGIN —
        Button(
            onClick = {
                viewModel.login(email = email.trim(), contrasena = password)
            },
            colors = ButtonDefaults.buttonColors(containerColor = accentColor),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("LOGIN", color = Color.Black, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(16.dp))

        // — Navegación a Registro —
        TextButton(onClick = { navController.navigate(Routes.Register.route) }) {
            Text("¿No tienes una cuenta?", color = Color.Black, fontWeight = FontWeight.SemiBold)
        }

        Spacer(Modifier.height(16.dp))

        Text("¿Olvidaste tu contraseña?", color = Color.Black, fontSize = 12.sp)
    }
}
