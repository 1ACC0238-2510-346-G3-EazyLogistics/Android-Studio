package pe.edu.upc.logisticmaster.presentation.view
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DetalleReservaScreen() {
    var detalle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Administración de reservas", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = detalle, onValueChange = { detalle = it }, label = { Text("Detalle de reserva") })

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { /* Aceptar lógica */ }) { Text("ACEPTAR") }
            Button(onClick = { /* Cancelar lógica */ }) { Text("CANCELAR") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { /* Ver estadísticas */ }) { Text("ESTADÍSTICAS") }
            Button(onClick = { /* Generar informe */ }) { Text("GENERAR INFORME") }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
            contentDescription = "Estadísticas",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { /* Volver */ }, modifier = Modifier.fillMaxWidth()) {
            Text("VOLVER A LA PANTALLA PRINCIPAL")
        }
    }
}
