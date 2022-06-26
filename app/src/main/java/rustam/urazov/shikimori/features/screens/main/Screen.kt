package rustam.urazov.shikimori.features.screens.main

import androidx.annotation.DrawableRes
import rustam.urazov.shikimori.R
import rustam.urazov.shikimori.features.PROFILE

sealed class Screen(val route: String, val label: String, @DrawableRes val icon: Int) {
    object Profile : Screen(PROFILE, PROFILE_LABEL, R.drawable.ic_profile)
}

private const val PROFILE_LABEL = "Профиль"