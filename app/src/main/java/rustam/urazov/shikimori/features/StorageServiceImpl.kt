package rustam.urazov.shikimori.features

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import rustam.urazov.shikimori.core.extention.empty
import rustam.urazov.shikimori.core.result.Result
import rustam.urazov.shikimori.core.result.Result.AccessToken
import rustam.urazov.shikimori.core.result.Result.RefreshToken
import rustam.urazov.shikimori.core.result.Result.Saved
import rustam.urazov.shikimori.features.models.AccessTokenStorage
import rustam.urazov.shikimori.features.models.RefreshTokenStorage
import rustam.urazov.shikimori.features.models.TokenStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageServiceImpl
@Inject constructor(@ApplicationContext context: Context) : StorageService {

    companion object {
        private const val SETTINGS = "settings"
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
    }

    private val sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)

    override fun saveTokens(tokenStorage: TokenStorage): Result<Nothing> {
        save(sharedPreferences, ACCESS_TOKEN, tokenStorage.accessTokenStorage.accessToken)
        save(sharedPreferences, REFRESH_TOKEN, tokenStorage.refreshTokenStorage.refreshToken)
        return Saved
    }

    override fun getAccessToken(): Result<AccessTokenStorage> =
        AccessToken(AccessTokenStorage(getString(sharedPreferences, ACCESS_TOKEN)))

    override fun getRefreshToken(): Result<RefreshTokenStorage> =
        RefreshToken(RefreshTokenStorage(getString(sharedPreferences, REFRESH_TOKEN)))
}

private fun save(sharedPreferences: SharedPreferences, key: String, value: String) =
    sharedPreferences.edit()
        .putString(key, "$BEARER $value")
        .apply()

private fun getString(sharedPreferences: SharedPreferences, key: String): String =
    sharedPreferences.getString(key, String.empty()).orEmpty()

private const val BEARER = "Bearer"