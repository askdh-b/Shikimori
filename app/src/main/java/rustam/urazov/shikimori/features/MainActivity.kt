package rustam.urazov.shikimori.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import rustam.urazov.shikimori.features.dialogs.error.ErrorDialog
import rustam.urazov.shikimori.features.screens.login.LogIn
import rustam.urazov.shikimori.features.screens.login.LogInViewModel
import rustam.urazov.shikimori.features.screens.main.Main
import rustam.urazov.shikimori.ui.theme.ShikimoriTheme
import rustam.urazov.shikimori.ui.theme.White

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainActivityViewModel: MainActivityViewModel = hiltViewModel()
            val systemUiController = rememberSystemUiController()
            val dialog by mainActivityViewModel.dialogState.collectAsState()

            SideEffect {
                systemUiController.setStatusBarColor(White)
            }

            ShikimoriTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = White
                ) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = LOG_IN) {
                        composable(LOG_IN) {
                            val logInViewModel: LogInViewModel = hiltViewModel()
                            LogIn(logInViewModel, mainActivityViewModel, navController)
                        }
                        composable(MAIN) {
                            Main()
                        }
                    }
                }

                ErrorDialog(
                    state = dialog,
                    onDismissRequest = { mainActivityViewModel.sendAction(MainActivityViewModel.Action.CloseDialog) },
                    onClick = { mainActivityViewModel.sendAction(MainActivityViewModel.Action.CloseDialog) }
                )
            }
        }
    }
}

const val LOG_IN = "logIn"
const val MAIN = "main"
const val PROFILE = "profile"