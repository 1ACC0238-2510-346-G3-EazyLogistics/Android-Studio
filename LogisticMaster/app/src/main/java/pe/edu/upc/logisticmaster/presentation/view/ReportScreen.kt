package pe.edu.upc.logisticmaster.presentation.view
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ReportScreen() {
    var titulo by remember { mutableStateOf("") }
    var parametro1 by remember { mutableStateOf("") }
    var parametro2 by remember { mutableStateOf("") }
    var parametro3 by remember { mutableStateOf("") }
    var parametro4 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("GENERAR INFORME", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = titulo, onValueChange = { titulo = it }, label = { Text("Título del informe") })
        OutlinedTextField(value = parametro1, onValueChange = { parametro1 = it }, label = { Text("Parámetro 1") })
        OutlinedTextField(value = parametro2, onValueChange = { parametro2 = it }, label = { Text("Parámetro 2") })
        OutlinedTextField(value = parametro3, onValueChange = { parametro3 = it }, label = { Text("Parámetro 3") })
        OutlinedTextField(value = parametro4, onValueChange = { parametro4 = it }, label = { Text("Parámetro 4") })

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { /* Cancelar lógica */ }) { Text("CANCELAR") }
            Button(onClick = { /* Generar lógica */ }) { Text("GENERAR") }
        }
    }
}
