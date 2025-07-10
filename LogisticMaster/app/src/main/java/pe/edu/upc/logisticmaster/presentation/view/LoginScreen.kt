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
fun LoginView(navController: NavController, viewModel: AuthViewModel) {
    val backgroundColor = Color.White
    val accentColor = Color(0xFF10BEAE)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val authState by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo superior
        Image(
            painter = painterResource(id = R.drawable.iconologistics),
            contentDescription = "Logo",
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 24.dp)
        )

        // Título debajo del logo
        Text(
            text = "Logistics Master",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Email
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

        Spacer(modifier = Modifier.height(12.dp))

        // Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("CONTRASEÑA") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = null, tint = accentColor)
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = accentColor,
                unfocusedBorderColor = accentColor,
                focusedLabelColor = accentColor,
                unfocusedLabelColor = accentColor
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Estado
        when (authState) {
            is AuthUiState.Loading -> CircularProgressIndicator(color = accentColor)
            is AuthUiState.Error -> Text(
                text = (authState as AuthUiState.Error).message,
                color = Color.Red
            )
            else -> Unit
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón login
        Button(
            onClick = {
                viewModel.login(usuario = email, contrasena = password)
            },
            colors = ButtonDefaults.buttonColors(containerColor = accentColor),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("LOGIN", color = Color.Black, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Registro
        TextButton(onClick = { navController.navigate(Routes.Register.route) }) {
            Text("¿No tienes una cuenta?", color = Color.Black, fontWeight = FontWeight.SemiBold)
        }

        // Registro botón
        Button(
            onClick = { navController.navigate(Routes.Register.route) },
            colors = ButtonDefaults.buttonColors(containerColor = accentColor),
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = 4.dp)
        ) {
            Text("REGISTRARSE", fontWeight = FontWeight.Bold, color = Color.Black)
        }

        // ¿Olvidaste tu contraseña?
        Spacer(modifier = Modifier.height(16.dp))
        Text("¿Olvidaste tu contraseña?", color = Color.Black, fontSize = 12.sp)
    }
}
