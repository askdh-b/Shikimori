package rustam.urazov.shikimori.features.screens.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import rustam.urazov.shikimori.features.MainActivityViewModel

@Composable
fun Profile(viewModel: ProfileViewModel, parentViewModel: MainActivityViewModel) {
    val failure by viewModel.failure.collectAsState()
}