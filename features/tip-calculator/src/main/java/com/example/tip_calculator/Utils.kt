package com.example.tip_calculator

import arrow.core.Either
import arrow.core.Option
import arrow.core.Some

inline fun <LEFT, RIGHT, TYPE> Either<LEFT, RIGHT>.foldError(
    onError: (LEFT) -> TYPE
): TYPE? {
    if (this is Either.Left)
        return onError(value)
    return null
}

inline fun <LEFT, RIGHT, TYPE> Either<LEFT, RIGHT>.foldSuccess(
    onSuccess: (RIGHT) -> TYPE
): TYPE? {
    if (this is Either.Right)
        return onSuccess(value)
    return null
}

inline fun <LEFT, RIGHT, TYPE> Either<LEFT, RIGHT>.fold(
    onError: (LEFT) -> TYPE,
    onSuccess: (RIGHT) -> TYPE
): TYPE = when (this) {
    is Either.Left -> onError(value)
    is Either.Right -> onSuccess(value)
}

fun <TYPE> Option<TYPE>.foldSuccess(): TYPE? {
    if (this is Some)
        return value
    return null
}