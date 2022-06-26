package rustam.urazov.shikimori.features.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import rustam.urazov.shikimori.features.MainActivityViewModel

@Composable
fun Main(viewModel: MainViewModel, parentViewModel: MainActivityViewModel) {
    val failure by viewModel.failure.collectAsState()
}