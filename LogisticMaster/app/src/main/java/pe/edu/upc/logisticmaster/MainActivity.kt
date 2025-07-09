package pe.edu.upc.logisticmaster

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.logisticmaster.presentation.di.ViewModelModule
import pe.edu.upc.logisticmaster.presentation.navigation.AppNav

class MainActivity : ComponentActivity() {
    private val authViewModel    by lazy { ViewModelModule.provideAuthViewModel() }
    private val workerViewModel  by lazy { ViewModelModule.provideWorkerViewModel() }
    private val taskViewModel    by lazy { ViewModelModule.provideTaskViewModel() }
    private val reserveViewModel by lazy { ViewModelModule.provideReserveViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppNav(
                navController     = navController,
                authViewModel     = authViewModel,
                workerViewModel   = workerViewModel,
                taskViewModel     = taskViewModel,
                reserveViewModel  = reserveViewModel
            )
        }
    }
}
