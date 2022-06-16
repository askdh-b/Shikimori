package rustam.urazov.shikimori.features.screens.login

import rustam.urazov.shikimori.core.exception.Failure
import rustam.urazov.shikimori.core.functional.Either
import rustam.urazov.shikimori.core.interactor.UseCase
import rustam.urazov.shikimori.core.result.Result
import rustam.urazov.shikimori.features.models.Token
import rustam.urazov.shikimori.features.repositories.TokenRepository
import javax.inject.Inject

class SaveTokens
@Inject constructor(private val tokenRepository: TokenRepository) :
    UseCase<Result<Nothing>, Token>() {

    override suspend fun run(params: Token): Either<Failure, Result<Nothing>> =
        tokenRepository.save(params)
}