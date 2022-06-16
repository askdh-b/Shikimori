package rustam.urazov.shikimori.features.models

data class TokenStorage(
    val accessTokenStorage: AccessTokenStorage,
    val refreshTokenStorage: RefreshTokenStorage
)