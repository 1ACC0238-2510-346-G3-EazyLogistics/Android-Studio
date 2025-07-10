import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Text
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerUiState
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerViewModel

@Composable
fun EditarPerfilView(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val user = authViewModel // Asumimos que ya tienes este método
    val accentColor = Color(0xFF10BEAE)

    var nombre by remember { mutableStateOf(user?.nombre ?: "") }
    var apellido by remember { mutableStateOf(user?.apellido ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Editar perfil",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .background(accentColor, RoundedCornerShape(8.dp))
                .padding(horizontal = 24.dp, vertical = 8.dp)
        )

        Spacer(Modifier.height(24.dp))

        // Campos de entrada
        listOf(
            "NOMBRE" to nombre,
            "APELLIDO" to apellido,
            "EMAIL" to email,
            "CONTRASEÑA" to password,
            "CONFIRMAR CONTRASEÑA" to confirmPassword
        ).forEachIndexed { index, pair ->
            Text(pair.first, fontWeight = FontWeight.Bold, color = Color.Black)
            OutlinedTextField(
                value = pair.second,
                onValueChange = {
                    when (index) {
                        0 -> nombre = it
                        1 -> apellido = it
                        2 -> email = it
                        3 -> password = it
                        4 -> confirmPassword = it
                    }
                },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = accentColor,
                    unfocusedContainerColor = accentColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
        }

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                // Validar y actualizar datos aquí
                // authViewModel.updateUser(...)
            },
            colors = ButtonDefaults.buttonColors(containerColor = accentColor),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text("Actualizar", fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
}
