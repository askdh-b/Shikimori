package rustam.urazov.shikimori.core.result

import rustam.urazov.shikimori.features.models.AccessTokenStorage
import rustam.urazov.shikimori.features.models.RefreshTokenStorage

sealed class Result<out T> {
    object Saved : Result<Nothing>()

    data class AccessToken<out T>(val accessToken: AccessTokenStorage): Result<T>()
    data class RefreshToken<out T>(val refreshToken: RefreshTokenStorage): Result<T>()
}