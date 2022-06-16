package rustam.urazov.shikimori.features

import rustam.urazov.shikimori.core.result.Result
import rustam.urazov.shikimori.features.models.AccessTokenStorage
import rustam.urazov.shikimori.features.models.RefreshTokenStorage
import rustam.urazov.shikimori.features.models.TokenStorage

interface StorageService {

    fun saveTokens(tokenStorage: TokenStorage): Result<Nothing>

    fun getAccessToken(): Result<AccessTokenStorage>

    fun getRefreshToken(): Result<RefreshTokenStorage>
}