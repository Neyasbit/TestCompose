package com.example.tip_calculator.data

import arrow.core.Either
import arrow.core.Nel
import arrow.core.Validated
import arrow.core.computations.either
import arrow.core.invalidNel
import arrow.core.validNel

@JvmInline
value class Tip(val outputNumber: Double)

@JvmInline
value class FromField(val value: String)

sealed class ValidationError(message: String) : Throwable(message) {
    object Empty : ValidationError("Empty value or blank!")
    data class MaxLength(val value: Int) : ValidationError("Exceeded length of $value")
    object NotATipNumber : ValidationError("Not a tip number")
}

/** strategies **/
sealed class Strategy {
    object FailFast : Strategy()
    object ErrorAccumulation : Strategy()
}

interface ValidationTipProcessor {

    suspend fun validation(input: String): Either<Nel<ValidationError>, Tip>

    class Base : ValidationTipProcessor {

        private fun FromField.emptyOrBlank() =
            if (value.isNotEmpty() || value.isNotBlank()) validNel()
            else ValidationError.Empty.invalidNel()

        private fun FromField.maxLength(maxLength: Int): Validated<Nel<ValidationError.MaxLength>, FromField> =
            if (value.length <= maxLength) validNel()
            else ValidationError.MaxLength(maxLength).invalidNel()

        private fun FromField.number() =
            if (value.toDoubleOrNull() != null) validNel()
            else ValidationError.NotATipNumber.invalidNel()


        private fun FromField.validateFailFast(): Either<Nel<ValidationError>, Tip> =
            either.eager {
                emptyOrBlank().bind() // fails fast on first error found
                maxLength(6).bind()
                number().bind()
                Tip(value.toDouble())
            }

        override suspend fun validation(input: String): Either<Nel<ValidationError>, Tip> =
            FromField(input).validateFailFast()
    }
}