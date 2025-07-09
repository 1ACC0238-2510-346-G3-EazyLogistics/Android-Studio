package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.navigation.Routes
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.AuthUiState
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.AuthViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.RegisterUiModel
import pe.edu.upc.logisticmaster.presentation.view.InputLabel
import pe.edu.upc.logisticmaster.presentation.view.ActionButton



@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: AuthViewModel
) {
    val backgroundColor = Color(0xFF10BEAE)
    val fieldColor = Color.White

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var usuario by remember { mutableStateOf("") }
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
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Registrar\nUsuario",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(fieldColor, RoundedCornerShape(12.dp))
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            )

            Spacer(Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = fieldColor),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    // Nombre
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor   = backgroundColor,
                            unfocusedContainerColor = backgroundColor,
                            focusedTextColor        = Color.Black,
                            unfocusedTextColor      = Color.Black,
                            focusedBorderColor      = Color.Transparent,
                            unfocusedBorderColor    = Color.Transparent
                        )
                    )
                    Spacer(Modifier.height(12.dp))
                    // Apellido
                    OutlinedTextField(
                        value = apellido,
                        onValueChange = { apellido = it },
                        label = { Text("Apellido") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor   = backgroundColor,
                            unfocusedContainerColor = backgroundColor,
                            focusedTextColor        = Color.Black,
                            unfocusedTextColor      = Color.Black,
                            focusedBorderColor      = Color.Transparent,
                            unfocusedBorderColor    = Color.Transparent
                        )
                    )
                    Spacer(Modifier.height(12.dp))
                    // Usuario
                    OutlinedTextField(
                        value = usuario,
                        onValueChange = { usuario = it },
                        label = { Text("Usuario") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor   = backgroundColor,
                            unfocusedContainerColor = backgroundColor,
                            focusedTextColor        = Color.Black,
                            unfocusedTextColor      = Color.Black,
                            focusedBorderColor      = Color.Transparent,
                            unfocusedBorderColor    = Color.Transparent
                        )
                    )
                    Spacer(Modifier.height(12.dp))
                    // Email
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor   = backgroundColor,
                            unfocusedContainerColor = backgroundColor,
                            focusedTextColor        = Color.Black,
                            unfocusedTextColor      = Color.Black,
                            focusedBorderColor      = Color.Transparent,
                            unfocusedBorderColor    = Color.Transparent
                        )
                    )
                    Spacer(Modifier.height(12.dp))
                    // Contraseña
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        singleLine = true,
                        label = { Text("Contraseña") },
                        visualTransformation = if (passwordVisible)
                            VisualTransformation.None
                        else
                            PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                val image = if (passwordVisible)
                                    Icons.Default.VisibilityOff
                                else
                                    Icons.Default.Visibility
                                val description = if (passwordVisible)
                                    "Ocultar contraseña"
                                else
                                    "Mostrar contraseña"
                                Icon(
                                    imageVector = image,
                                    contentDescription = description
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor   = backgroundColor,
                            unfocusedContainerColor = backgroundColor,
                            focusedBorderColor      = Color.Transparent,
                            unfocusedBorderColor    = Color.Transparent,
                            focusedTextColor        = Color.Black,
                            unfocusedTextColor      = Color.Black
                        )
                    )

                }
            }

            Spacer(Modifier.height(20.dp))

            when (authState) {
                is AuthUiState.Loading -> CircularProgressIndicator(color = Color.White)
                is AuthUiState.Error -> {
                    Text(
                        text = (authState as AuthUiState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                else -> Unit
            }

            // Botones estándar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text("CANCELAR", color = Color.Black, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = {
                        viewModel.register(
                            RegisterUiModel(
                                nombre     = nombre,
                                apellido   = apellido,
                                usuario    = usuario,
                                email      = email,
                                contrasena = password
                            )
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text("REGISTRAR", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.PersonAdd,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(48.dp)
            )
            Text("LogisticsMasters", color = Color.White, fontWeight = FontWeight.Bold)
            Text("Únete a nuestra plataforma", color = Color.White, fontSize = 12.sp)
        }
    }
}
