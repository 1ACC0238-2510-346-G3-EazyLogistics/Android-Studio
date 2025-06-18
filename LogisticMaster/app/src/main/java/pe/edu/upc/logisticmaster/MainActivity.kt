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
import pe.edu.upc.logisticmaster.presentation.navigation.AppNav
import pe.edu.upc.logisticmaster.presentation.viewmodel.AppViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.UserViewModel
import pe.edu.upc.logisticmaster.ui.theme.LogisticMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appViewModel: AppViewModel = viewModel()
            val isDarkModeEnabled by appViewModel.isDarkMode.collectAsState()
            LogisticMasterTheme (darkTheme = true ) {
                val navController = rememberNavController()
                val userViewModel: UserViewModel = viewModel()
                AppNav(navController = navController, userViewModel = userViewModel, appViewModel = appViewModel)
            }
        }
    }
}