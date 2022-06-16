package rustam.urazov.shikimori.features.screens.login

import rustam.urazov.shikimori.core.exception.Failure
import rustam.urazov.shikimori.core.functional.Either
import rustam.urazov.shikimori.core.interactor.UseCase
import rustam.urazov.shikimori.features.models.AuthorizationData
import rustam.urazov.shikimori.features.models.Token
import rustam.urazov.shikimori.features.repositories.TokenRepository
import javax.inject.Inject

class Authorize
@Inject constructor(private val tokenRepository: TokenRepository) :
    UseCase<Token, AuthorizationData>() {

    override suspend fun run(params: AuthorizationData): Either<Failure, Token> =
        tokenRepository.authorize(params)
}