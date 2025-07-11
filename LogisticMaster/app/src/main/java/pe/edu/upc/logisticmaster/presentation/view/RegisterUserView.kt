package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TempleHindu
import androidx.compose.material3.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.AuthUiState
import pe.edu.upc.logisticmaster.presentation.view.InputLabel
import pe.edu.upc.logisticmaster.presentation.view.CustomInputField
import pe.edu.upc.logisticmaster.presentation.view.ActionButton

@Composable
fun RegisterUserView(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val backgroundColor = Color(0xFF10BEAE)
    val fieldColor = Color.White

    val authState by authViewModel.authState.collectAsState()
    val currentUser by authViewModel.currentUser.collectAsState()

    var username by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Navigate to menu if user is registered
    LaunchedEffect(currentUser) {
        if (currentUser != null) {
            navController.navigate(Routes.Menu.route) {
                popUpTo(Routes.Register.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Registrar\nusuario",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Error/Success message
            when (authState) {
                is AuthUiState.Error -> {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.Red.copy(alpha = 0.1f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = (authState as AuthUiState.Error).message,
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                is AuthUiState.Success -> {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.Green.copy(alpha = 0.1f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = (authState as AuthUiState.Success).message,
                            color = Color.Green,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                else -> {}
            }

            // Formulario
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = fieldColor),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    InputLabel("USUARIO")
                    CustomInputField(
                        value = username,
                        onValueChange = { username = it }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InputLabel("NOMBRE")
                    CustomInputField(
                        value = nombre,
                        onValueChange = { nombre = it }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InputLabel("APELLIDO")
                    CustomInputField(
                        value = apellido,
                        onValueChange = { apellido = it }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InputLabel("EMAIL")
                    CustomInputField(
                        value = email,
                        onValueChange = { email = it }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InputLabel("CONTRASEÑA")
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFF10BEAE),
                            unfocusedContainerColor = Color(0xFF10BEAE),
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InputLabel("CONFIRMAR CONTRASEÑA")
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFF10BEAE),
                            unfocusedContainerColor = Color(0xFF10BEAE),
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation()
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botones
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionButton(
                    text = "REGISTRAR",
                    onClick = {
                        if (password == confirmPassword) {
                            authViewModel.register(username, nombre, apellido, email, password)
                        } else {
                            // Show error for password mismatch
                        }
                    }
                )
                ActionButton(
                    text = "VOLVER",
                    onClick = { navController.popBackStack() }
                )
            }
        }

        }
    }