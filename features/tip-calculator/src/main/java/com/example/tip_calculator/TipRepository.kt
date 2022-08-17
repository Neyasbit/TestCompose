package com.example.tip_calculator

import arrow.core.Either
import com.example.tip_calculator.data.CalculateTip
import com.example.tip_calculator.data.CalculateTip.Companion.DEFAULT_TIP_PERCENT
import com.example.tip_calculator.data.ValidationError
import com.example.tip_calculator.data.ValidationTipProcessor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface TipRepository {

    suspend fun fetchTip(
        inputUserValue: String, tipPercent: Float = DEFAULT_TIP_PERCENT
    ): Either<ValidationError, String>

    class Base(
        private val calculateTip: CalculateTip,
        private val validationTipProcessor: ValidationTipProcessor,
        private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default
    ) : TipRepository {

        override suspend fun fetchTip(
            inputUserValue: String,
            tipPercent: Float
        ): Either<ValidationError, String> =
            withContext(coroutineDispatcher) {
                validationTipProcessor.validation(inputUserValue)
                    .mapLeft { it.head }
                    .map {
                        val formattedNumber = it.outputNumber
                        calculateTip.calculate(formattedNumber, tipPercent)
                    }
            }
    }
}