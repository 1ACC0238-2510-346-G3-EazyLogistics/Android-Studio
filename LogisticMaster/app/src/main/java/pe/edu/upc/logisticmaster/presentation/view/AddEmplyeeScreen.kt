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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.navigation.Routes
import pe.edu.upc.logisticmaster.presentation.viewmodel.AppViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.AuthUiState
import pe.edu.upc.logisticmaster.presentation.viewmodel.AppViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.AppViewModel


@Composable
fun RegisterUserScreen(
    navController: NavController,
    viewModel: AppViewModel = hiltViewModel()
) {
    val backgroundColor = Color(0xFF10BEAE)
    val fieldColor = Color.White

    // Estados de los campos
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var usuario by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Estado de la petición
    val authState by viewModel.authState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Título
            Text(
                text = "Registrar\nUsuario",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Formulario
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = fieldColor),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    InputLabel("NOMBRE")
                    CustomInputField(nombre) { nombre = it }

                    Spacer(Modifier.height(12.dp))
                    InputLabel("APELLIDO")
                    CustomInputField(apellido) { apellido = it }

                    Spacer(Modifier.height(12.dp))
                    InputLabel("USUARIO")
                    CustomInputField(usuario) { usuario = it }

                    Spacer(Modifier.height(12.dp))
                    InputLabel("EMAIL")
                    CustomInputField(email) { email = it }

                    Spacer(Modifier.height(12.dp))
                    InputLabel("CONTRASEÑA")
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        singleLine = true,
                        visualTransformation = if (passwordVisible)
                            VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton { passwordVisible = !passwordVisible } {
                                Icon(
                                    imageVector =
                                        if (passwordVisible) Icons.Default.VisibilityOff
                                        else Icons.Default.Visibility,
                                    contentDescription = null
                                )
                            }
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = backgroundColor,
                            unfocusedContainerColor = backgroundColor,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // Botones y feedback
            when (authState) {
                is pe.edu.upc.logisticmaster.presentation.viewmodel.AuthState.Loading -> {
                    CircularProgressIndicator(color = Color.White)
                }
                is pe.edu.upc.logisticmaster.presentation.viewmodel.AuthState.Error -> {
                    val msg = (authState as pe.edu.upc.logisticmaster.presentation.viewmodel.AuthState.Error).message
                    Text(text = msg, color = Color.Red)
                }
                else -> Unit
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                ActionButton("CANCELAR") {
                    navController.popBackStack()
                }
                ActionButton("REGISTRAR") {
                    viewModel.register(
                        nombre = nombre,
                        apellido = apellido,
                        usuario = usuario,
                        email = email,
                        contrasena = password
                    )
                }
            }
        }

        // Pie de página
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

@Composable
private fun InputLabel(text: String) {
    Text(text = text, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
}

@Composable
private fun CustomInputField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
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
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun ActionButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
    ) {
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}
