package rustam.urazov.shikimori.core.functional

sealed class Either<out L, out R> {
    data class Left<out L>(val f: L) : Either<L, Nothing>()
    data class Right<out R>(val s: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun <L> left(f: L) = Left(f)

    fun <R> right(s: R) = Right(s)

    fun fold(funL: (L) -> Any, funR: (R) -> Any): Any = when (this) {
        is Left -> funL(f)
        is Right -> funR(s)
    }
}

fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

fun <T, L, R> Either<L, R>.flatMap(funR: (R) -> Either<L, T>): Either<L, T> = when (this) {
    is Either.Left -> Either.Left(f)
    is Either.Right -> funR(s)
}

fun <T, L, R> Either<L, R>.map(funR: (R) -> Either<L, T>) = this.flatMap(funR.c(::right))

fun <L, R> Either<L, R>.getOrElse(value: R): R = when (this) {
    is Either.Left -> value
    is Either.Right -> s
}

fun <L, R> Either<L, R>.onFailure(funL: (failure: L) -> Unit): Either<L, R> =
    this.apply { if (this is Either.Left) funL(f) }

fun <L, R> Either<L, R>.onSuccess(funR: (success: R) -> Unit): Either<L, R> =
    this.apply { if (this is Either.Right) funR(s) }