package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
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
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.AuthViewModel
import androidx.compose.ui.text.input.PasswordVisualTransformation
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.AuthUiState

@Composable
fun UserProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val backgroundColor = Color(0xFF10BEAE)
    val currentUser by authViewModel.currentUser.collectAsState()
    val authState by authViewModel.authState.collectAsState()

    var isEditing by remember { mutableStateOf(false) }
    var editedUsername by remember { mutableStateOf("") }
    var editedNombre by remember { mutableStateOf("") }
    var editedApellido by remember { mutableStateOf("") }
    var editedEmail by remember { mutableStateOf("") }
    var editedPassword by remember { mutableStateOf("") }

    // Initialize form with current user data
    LaunchedEffect(currentUser) {
        currentUser?.let { user ->
            editedUsername = user.usuario
            editedNombre = user.nombre
            editedApellido = user.apellido
            editedEmail = user.email
            editedPassword = ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        // Header with back button and title
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = if (isEditing) "Editar Perfil" else "Mi Perfil",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            if (!isEditing) {
                IconButton(
                    onClick = { isEditing = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(48.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

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

        // Profile content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            if (isEditing) {
                // Edit form
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        InputLabel("USUARIO")
                        CustomInputField(
                            value = editedUsername,
                            onValueChange = { editedUsername = it }
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        InputLabel("NOMBRE")
                        CustomInputField(
                            value = editedNombre,
                            onValueChange = { editedNombre = it }
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        InputLabel("APELLIDO")
                        CustomInputField(
                            value = editedApellido,
                            onValueChange = { editedApellido = it }
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        InputLabel("EMAIL")
                        CustomInputField(
                            value = editedEmail,
                            onValueChange = { editedEmail = it }
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        InputLabel("NUEVA CONTRASEÑA (opcional)")
                        OutlinedTextField(
                            value = editedPassword,
                            onValueChange = { editedPassword = it },
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

                Spacer(modifier = Modifier.height(16.dp))

                // Action buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ActionButton(
                        text = "GUARDAR",
                        onClick = {
                            currentUser?.let { user ->
                                authViewModel.updateProfile(
                                    user.id!!,
                                    editedUsername,
                                    editedNombre,
                                    editedApellido,
                                    editedEmail,
                                    if (editedPassword.isNotBlank()) editedPassword else null
                                )
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )

                    ActionButton(
                        text = "CANCELAR",
                        onClick = {
                            isEditing = false
                            authViewModel.clearError()
                            // Reset form to original values
                            currentUser?.let { user ->
                                editedUsername = user.usuario
                                editedNombre = user.nombre
                                editedApellido = user.apellido
                                editedEmail = user.email
                                editedPassword = ""
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            } else {
                // Display mode
                currentUser?.let { user ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(6.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            ProfileInfoRow("Usuario", user.usuario)
                            ProfileInfoRow("Nombre", user.nombre)
                            ProfileInfoRow("Apellido", user.apellido)
                            ProfileInfoRow("Email", user.email)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
        Text(
            text = value,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
} 