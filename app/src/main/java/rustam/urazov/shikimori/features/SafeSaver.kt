package rustam.urazov.shikimori.features

import rustam.urazov.shikimori.core.exception.Failure
import rustam.urazov.shikimori.core.functional.Either
import rustam.urazov.shikimori.core.result.Result
import rustam.urazov.shikimori.features.models.ErrorResponse

fun <T> safeSaver(result: Result<T>): Either<Failure, Result<T>> = try {
    Either.Right(result)
} catch (t: Throwable) {
    Either.Left(Failure.StorageError(ErrorResponse(t.message.orEmpty())))
}