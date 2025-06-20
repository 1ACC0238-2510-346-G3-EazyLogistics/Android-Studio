package pe.edu.upc.logisticmaster.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ReportScreen(navController: NavController) {
    val backgroundColor = Color(0xFF10BEAE)
    val cardColor = Color(0xFFFFFFFF)
    val textColor = Color(0xFF000000)

    var titulo by remember { mutableStateOf("") }
    var parametro1 by remember { mutableStateOf("") }
    var parametro2 by remember { mutableStateOf("") }
    var parametro3 by remember { mutableStateOf("") }
    var parametro4 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = cardColor),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "GENERAR INFORME",
                    fontSize = 24.sp,
                    color = textColor
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título del informe") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = parametro1,
                    onValueChange = { parametro1 = it },
                    label = { Text("Parámetro 1") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = parametro2,
                    onValueChange = { parametro2 = it },
                    label = { Text("Parámetro 2") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = parametro3,
                    onValueChange = { parametro3 = it },
                    label = { Text("Parámetro 3") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = parametro4,
                    onValueChange = { parametro4 = it },
                    label = { Text("Parámetro 4") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                        navController.popBackStack() // volver a pantalla anterior
                    }) {
                        Text("CANCELAR")
                    }
                }
            }
        }
    }
}
