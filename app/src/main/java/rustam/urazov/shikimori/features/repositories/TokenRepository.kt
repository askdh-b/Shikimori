package rustam.urazov.shikimori.features.repositories

import rustam.urazov.shikimori.core.exception.Failure
import rustam.urazov.shikimori.core.functional.Either
import rustam.urazov.shikimori.core.result.Result
import rustam.urazov.shikimori.features.models.*

interface TokenRepository {

    suspend fun authorize(authorizationData: AuthorizationData): Either<Failure, Token>

    fun save(token: Token): Either<Failure, Result<Nothing>>

}