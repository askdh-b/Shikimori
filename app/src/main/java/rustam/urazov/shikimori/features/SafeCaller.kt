package rustam.urazov.shikimori.features

import okhttp3.ResponseBody
import retrofit2.Response
import rustam.urazov.shikimori.core.exception.Failure
import rustam.urazov.shikimori.core.exception.Failure.ServerError
import rustam.urazov.shikimori.core.extention.empty
import rustam.urazov.shikimori.core.functional.Either
import rustam.urazov.shikimori.core.functional.Either.Left
import rustam.urazov.shikimori.core.functional.Either.Right
import rustam.urazov.shikimori.features.models.ErrorResponse

suspend fun <T, R> safeCall(
    call: suspend () -> Response<T>,
    transform: (T) -> R,
    default: T
): Either<Failure, R> = try {
    val response = call.invoke()
    when {
        response.isSuccessful -> {
            Right(transform(response.body() ?: default))
        }
        response.code() == 401 -> {
            Left(Failure.AuthorizationError)
        }
        else -> {
            Left(
                ServerError(
                    ErrorResponse(
                        convertErrorBody(response.errorBody()) ?: UNEXPECTED_SERVER_ERROR
                    )
                )
            )
        }
    }
} catch (t: Throwable) {
    Left(ServerError(ErrorResponse(t.message.orEmpty())))
}

private fun convertErrorBody(responseBody: ResponseBody?): String? {
    val response = responseBody?.string() ?: ""
    val error = Regex(""""error_description":".*"""").find(response)
    return error?.value
        ?.replace(""""""", String.empty())
        ?.replace("error_description", String.empty())
        ?.replace(":", String.empty())
}

private const val UNEXPECTED_SERVER_ERROR = "Неизвестная ошибка сервера"