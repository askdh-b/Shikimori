package rustam.urazov.shikimori.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import rustam.urazov.shikimori.features.screens.login.LogIn
import rustam.urazov.shikimori.features.screens.login.LogInViewModel
import rustam.urazov.shikimori.ui.theme.ShikimoriTheme
import rustam.urazov.shikimori.ui.theme.White

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemUiController = rememberSystemUiController()

            SideEffect {
                systemUiController.setStatusBarColor(color = White)
            }

            ShikimoriTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = White
                ) {
                    val navController = rememberNavController()

                    NavHost (navController = navController, startDestination = LOG_IN) {
                        composable(route = LOG_IN) {
                            val logInViewModel: LogInViewModel = hiltViewModel()
                            LogIn(logInViewModel)
                        }
                    }
                }
            }
        }
    }
}

private const val LOG_IN = "logIn"