package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.navigation.Routes
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.AuthUiState
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.AuthViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.RegisterUiModel
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldDefaults
import kotlinx.coroutines.delay

@Composable
fun RegisterView(
    navController: NavController,
    viewModel: AuthViewModel
) {
    val mainColor = Color(0xFF10BEAE)
    val authState by viewModel.state.collectAsState()

    // Para manejar Snackbar
    val snackbarHostState = remember { SnackbarHostState() }

    // Variables de formulario
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmVisible by remember { mutableStateOf(false) }

    // Cuando el registro sea exitoso, mostramos snackbar y navegamos
    LaunchedEffect(authState) {
        if (authState is AuthUiState.Success) {
            snackbarHostState.showSnackbar("Registro exitoso")
            // Pequeña pausa para que el usuario vea el mensaje
            delay(1000)
            navController.navigate(Routes.Login.route) {
                popUpTo(Routes.Register.route) { inclusive = true }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(24.dp)
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "REGISTRARSE",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .background(mainColor, RoundedCornerShape(8.dp))
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            )

            Spacer(Modifier.height(24.dp))

            // Helper para campos de texto
            @Composable
            fun CustomTextField(
                value: String,
                onValueChange: (String) -> Unit,
                label: String
            ) {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    label = { Text(label.uppercase(), fontSize = 12.sp, fontWeight = FontWeight.Bold) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }

            CustomTextField(nombre, { nombre = it }, "Nombre")
            CustomTextField(apellido, { apellido = it }, "Apellido")
            CustomTextField(email, { email = it }, "Email")

            Spacer(Modifier.height(8.dp))

            // Contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("CONTRASEÑA", fontSize = 12.sp, fontWeight = FontWeight.Bold) },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )

            // Confirmar contraseña con toggle
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("CONFIRMAR CONTRASEÑA", fontSize = 12.sp, fontWeight = FontWeight.Bold) },
                singleLine = true,
                visualTransformation = if (confirmVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { confirmVisible = !confirmVisible }) {
                        Icon(
                            imageVector = if (confirmVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )

            Spacer(Modifier.height(20.dp))

            // Mostrar error o loading
            when (authState) {
                is AuthUiState.Error -> Text(
                    text = (authState as AuthUiState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                is AuthUiState.Loading -> CircularProgressIndicator(color = mainColor)
                else -> Unit
            }

            // Botón Confirmar
            Button(
                onClick = {
                    viewModel.register(
                        RegisterUiModel(
                            nombre = nombre,
                            apellido = apellido,
                            usuario = email,
                            email = email,
                            contrasena = password
                        )
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = mainColor),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Confirmar", fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}