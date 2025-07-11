package pe.edu.upc.logisticmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.logisticmaster.presentation.di.ViewModelModule
import pe.edu.upc.logisticmaster.presentation.navigation.AppNav
import pe.edu.upc.logisticmaster.presentation.viewmodel.auth.AuthViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.worker.WorkerViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.task.TaskViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.reserve.ReserveViewModel
import pe.edu.upc.logisticmaster.ui.theme.LogisticMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LogisticMasterTheme(darkTheme = false) {
                val navController = rememberNavController()
                
                val authViewModel: AuthViewModel = viewModel { ViewModelModule.provideAuthViewModel() }
                val workerViewModel: WorkerViewModel = viewModel { ViewModelModule.provideWorkerViewModel() }
                val taskViewModel: TaskViewModel = viewModel { ViewModelModule.provideTaskViewModel() }
                val reserveViewModel: ReserveViewModel = viewModel { ViewModelModule.provideReserveViewModel() }
                
                AppNav(
                    navController = navController,
                    authViewModel = authViewModel,
                    workerViewModel = workerViewModel,
                    taskViewModel = taskViewModel,
                    reserveViewModel = reserveViewModel
                )
            }
        }
    }
}
