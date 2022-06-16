package rustam.urazov.shikimori.core.exception

import rustam.urazov.shikimori.features.models.ErrorResponse

sealed class Failure {
    object BaseFailure : Failure()
    object NetworkConnection : Failure()
    object AuthorizationError : Failure()

    data class ServerError(val errorResponse: ErrorResponse) : Failure()
    data class StorageError(val errorResponse: ErrorResponse) : Failure()

    abstract class FeatureFailure : Failure()
}