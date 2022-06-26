package rustam.urazov.shikimori.features.screens.main

import rustam.urazov.shikimori.features.PROFILE

sealed class Screen(val route: String, val label: String) {
    object Profile : Screen(PROFILE, PROFILE_LABEL)
}

private const val PROFILE_LABEL = "Профиль"