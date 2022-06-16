package rustam.urazov.shikimori.core.interactor

import kotlinx.coroutines.*
import rustam.urazov.shikimori.core.exception.Failure
import rustam.urazov.shikimori.core.functional.Either

abstract class UseCase<out Type, in Params> where Type: Any {

    abstract suspend fun run(params: Params) : Either<Failure, Type>

    operator fun invoke(
        params: Params,
        scope: CoroutineScope = GlobalScope,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                run(params)
            }
            onResult(deferred.await())
        }
    }
}