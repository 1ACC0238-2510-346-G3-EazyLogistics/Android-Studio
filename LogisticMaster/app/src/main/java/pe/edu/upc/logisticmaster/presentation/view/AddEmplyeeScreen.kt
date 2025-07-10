import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TempleHindu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.logisticmaster.presentation.view.InputLabel
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerFormState
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerUiState
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerViewModel
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import pe.edu.upc.logisticmaster.R
import pe.edu.upc.logisticmaster.presentation.view.ActionButton


 @Composable
 fun AddEmployeeScreen(
     navController: NavController,
     workerViewModel: WorkerViewModel
 ) {
     val backgroundColor = Color.White
     val fieldColor = Color(0xFF10BEAE)

     LaunchedEffect(Unit) {
         workerViewModel.updateForm { WorkerFormState() }
     }

     val form by workerViewModel.formState.collectAsState()
     val uiState by workerViewModel.uiState.collectAsState()

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
                 text = "Añadir\nempleado",
                 fontSize = 28.sp,
                 fontWeight = FontWeight.Bold,
                 color = Color.Black,
                 modifier = Modifier
                     .background(fieldColor, RoundedCornerShape(12.dp))
                     .padding(horizontal = 24.dp, vertical = 8.dp),
                 textAlign = TextAlign.Center
             )

             Spacer(Modifier.height(20.dp))

             Card(
                 modifier = Modifier.fillMaxWidth(),
                 colors = CardDefaults.cardColors(containerColor = fieldColor),
                 shape = RoundedCornerShape(16.dp),
                 elevation = CardDefaults.cardElevation(6.dp)
             ) {
                 Column(Modifier.padding(16.dp)) {
                     InputLabel("NOMBRE")
                     OutlinedTextField(
                         value = form.nombre,
                         onValueChange = { workerViewModel.updateForm { copy(nombre = it) } },
                         singleLine = true,
                         modifier = Modifier.fillMaxWidth(),
                         colors = OutlinedTextFieldDefaults.colors(
                             focusedTextColor = Color.Black,
                             unfocusedTextColor = Color.Black,
                             focusedContainerColor = Color.White,
                             unfocusedContainerColor = Color.White,
                             focusedBorderColor = Color.White,
                             unfocusedBorderColor = Color.White
                         )
                     )

                     Spacer(Modifier.height(12.dp))

                     InputLabel("EMAIL")
                     OutlinedTextField(
                         value = form.email,
                         onValueChange = { workerViewModel.updateForm { copy(email = it) } },
                         singleLine = true,
                         modifier = Modifier.fillMaxWidth(),
                         colors = OutlinedTextFieldDefaults.colors(
                             focusedTextColor = Color.Black,
                             unfocusedTextColor = Color.Black,
                             focusedContainerColor = Color.White,
                             unfocusedContainerColor = Color.White,
                             focusedBorderColor = Color.White,
                             unfocusedBorderColor = Color.White
                         )
                     )

                     Spacer(Modifier.height(12.dp))

                     InputLabel("NUMERO DE TELEFONO")
                     OutlinedTextField(
                         value = form.telefono,
                         onValueChange = { workerViewModel.updateForm { copy(telefono = it) } },
                         singleLine = true,
                         modifier = Modifier.fillMaxWidth(),
                         colors = OutlinedTextFieldDefaults.colors(
                             focusedTextColor = Color.Black,
                             unfocusedTextColor = Color.Black,
                             focusedContainerColor = Color.White,
                             unfocusedContainerColor = Color.White,
                             focusedBorderColor = Color.White,
                             unfocusedBorderColor = Color.White
                         )
                     )

                     Spacer(Modifier.height(12.dp))

                     Row(
                         modifier = Modifier.fillMaxWidth(),
                         horizontalArrangement = Arrangement.spacedBy(12.dp)
                     ) {
                         Column(Modifier.weight(1f)) {
                             InputLabel("PUESTO")
                             OutlinedTextField(
                                 value = form.puesto,
                                 onValueChange = { workerViewModel.updateForm { copy(puesto = it) } },
                                 singleLine = true,
                                 modifier = Modifier.fillMaxWidth(),
                                 colors = OutlinedTextFieldDefaults.colors(
                                     focusedTextColor = Color.Black,
                                     unfocusedTextColor = Color.Black,
                                     focusedContainerColor = Color.White,
                                     unfocusedContainerColor = Color.White,
                                     focusedBorderColor = Color.White,
                                     unfocusedBorderColor = Color.White
                                 )
                             )
                         }
                         Column(Modifier.weight(1f)) {
                             InputLabel("AREA")
                             OutlinedTextField(
                                 value = form.area,
                                 onValueChange = { workerViewModel.updateForm { copy(area = it) } },
                                 singleLine = true,
                                 modifier = Modifier.fillMaxWidth(),
                                 colors = OutlinedTextFieldDefaults.colors(
                                     focusedTextColor = Color.Black,
                                     unfocusedTextColor = Color.Black,
                                     focusedContainerColor = Color.White,
                                     unfocusedContainerColor = Color.White,
                                     focusedBorderColor = Color.White,
                                     unfocusedBorderColor = Color.White
                                 )
                             )
                         }
                     }
                 }
             }

             Spacer(Modifier.height(20.dp))

             when (uiState) {
                 is WorkerUiState.Loading -> CircularProgressIndicator(color = fieldColor)
                 is WorkerUiState.Error -> Text(
                     text = (uiState as WorkerUiState.Error).message,
                     color = Color.Red
                 )
                 else -> {}
             }

             Row(
                 modifier = Modifier.fillMaxWidth(),
                 horizontalArrangement = Arrangement.spacedBy(12.dp)
             ) {
                 Button(
                     onClick = { navController.popBackStack() },
                     modifier = Modifier
                         .weight(1f)
                         .height(48.dp),
                     colors = ButtonDefaults.buttonColors(containerColor = fieldColor)
                 ) {
                     Text("CANCELAR", color = Color.Black, fontWeight = FontWeight.Bold)
                 }
                 Button(
                     onClick = { workerViewModel.createWorker() },
                     modifier = Modifier
                         .weight(1f)
                         .height(48.dp),
                     colors = ButtonDefaults.buttonColors(containerColor = fieldColor)
                 ) {
                     Text("AÑADIR", color = Color.Black, fontWeight = FontWeight.Bold)
                 }
             }
         }

         // Footer
         Column(
             horizontalAlignment = Alignment.CenterHorizontally
         ) {
             Image(
                 painter = painterResource(id = R.drawable.iconologistics),
                 contentDescription = "Icono Logistics",
                 modifier = Modifier.size(80.dp)
             )
         }
     }
 }
