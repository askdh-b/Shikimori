package rustam.urazov.shikimori.features.repositories

import rustam.urazov.shikimori.core.exception.Failure
import rustam.urazov.shikimori.core.functional.Either
import rustam.urazov.shikimori.core.handler.NetworkHandler
import rustam.urazov.shikimori.core.result.Result
import rustam.urazov.shikimori.features.ShikimoriService
import rustam.urazov.shikimori.features.StorageService
import rustam.urazov.shikimori.features.models.*
import rustam.urazov.shikimori.features.models.TokenResponse.Companion.empty
import rustam.urazov.shikimori.features.safeSaver
import rustam.urazov.shikimori.features.safeCall
import javax.inject.Inject

class TokenRepositoryImpl
@Inject constructor(
    private val networkHandler: NetworkHandler,
    private val shikimoriService: ShikimoriService,
    private val storageService: StorageService
) : TokenRepository {

    override suspend fun authorize(authorizationData: AuthorizationData): Either<Failure, Token> =
        when (networkHandler.isNetworkAvailable()) {
            true -> safeCall(
                { shikimoriService.getAccessToken(authorizationData.mapToTokenBody()) },
                { it.mapToToken() },
                empty
            )
            false -> Either.Left(Failure.NetworkConnection)
        }

    override fun save(token: Token): Either<Failure, Result<Nothing>> =
        safeSaver(
            storageService.saveTokens(
                TokenStorage(
                    AccessTokenStorage(token.accessToken), RefreshTokenStorage(token.refreshToken)
                )
            )
        )
}