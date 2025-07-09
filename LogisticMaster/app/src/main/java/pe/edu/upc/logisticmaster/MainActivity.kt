package pe.edu.upc.logisticmaster.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.logisticmaster.presentation.navigation.AppNav
import pe.edu.upc.logisticmaster.presentation.viewmodel.AppViewModel
import pe.edu.upc.logisticmaster.presentation.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {

    private val appViewModel:   AppViewModel   by viewModels()
    private val userViewModel:  UserViewModel  by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppNav(
                navController   = navController,
                userViewModel   = userViewModel,
                appViewModel    = appViewModel
            )
        }
    }
}
