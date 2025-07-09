package pe.edu.upc.logisticmaster.presentation.view
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.LocalParking
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.TempleHindu
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.navigation.Routes
import pe.edu.upc.logisticmaster.presentation.viewmodel.AppViewModel

@Composable
fun RegisterScreen(navController: NavController, appViewModel: AppViewModel) {
    var usuario by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val isDarkTheme by appViewModel.isDarkMode.collectAsState()
    val backgroundColor = Color(0xFF10BEAE)
    val inputBackground = Color.White
    val inputTextColor = Color.Black
    val titleColor = Color.Black
    val accentColor = Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 24.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Logistics Master",
                textAlign = TextAlign.Center,
                color = titleColor,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,

            )

            Spacer(modifier = Modifier.height(32.dp))

            InputField(label = "USERNAME", value = usuario, onValueChange = { usuario = it })
            Spacer(modifier = Modifier.height(16.dp))
            InputField(label = "NOMBRE", value = nombre, onValueChange = { nombre = it })
            Spacer(modifier = Modifier.height(16.dp))
            InputField(label = "APELLIDO", value = apellido, onValueChange = { apellido = it })
            Spacer(modifier = Modifier.height(16.dp))
            InputField(label = "EMAIL", value = email, onValueChange = { email = it })
            Spacer(modifier = Modifier.height(16.dp))
            InputField(label = "CONTRASEÑA", value = password, onValueChange = { password = it }, isPassword = true)
            Spacer(modifier = Modifier.height(16.dp))
            InputField(label = "CONFIRMAR CONTRASEÑA", value = confirmPassword, onValueChange = { confirmPassword = it }, isPassword = true)
        }
// Enhanced login button
        val isFormValid = email.isNotBlank() && password.isNotBlank()

        Button(
            onClick = {
                navController.navigate(Routes.Login.route) {
                    popUpTo(Routes.Register.route) { inclusive = true }
                }
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "reset_terms",
                    true
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
               Color.Cyan
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = if (isFormValid) 8.dp else 2.dp
            ),
            enabled = isFormValid
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Login,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Iniciar Sesión",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "LogisticsMasters",
                color = accentColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Potenciando la experiencia hotelera",
                color = accentColor,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun InputField(label: String, value: String, onValueChange: (String) -> Unit, isPassword: Boolean = false) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp)
        )
    }
}
