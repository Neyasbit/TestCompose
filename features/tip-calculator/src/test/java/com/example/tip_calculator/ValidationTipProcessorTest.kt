package com.example.tip_calculator

import arrow.core.getOrElse
import com.example.tip_calculator.data.Tip
import com.example.tip_calculator.data.ValidationError
import com.example.tip_calculator.data.ValidationTipProcessor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
internal class ValidationTipProcessorTest {

    private val validationTipProcessor = ValidationTipProcessor.Base()

    @Test
    fun validationEmptyTest() = runTest {
        val emptyInput = validationTipProcessor.validation("")
        assert(emptyInput.isLeft())
        emptyInput.foldError { assert(it.head is ValidationError.Empty) }
    }

    @Test
    fun validationGoodNumber() = runTest {
        val number = validationTipProcessor.validation("7707")
        assert(number.isRight())
        assert(number.getOrElse { Tip(0.0) } == Tip(7707.0))
    }

    @Test
    fun validationGoodNumberTwo() = runTest {
        val number = validationTipProcessor.validation("0.7707")
        assert(number.isRight())
        number.foldSuccess { Assert.assertEquals(Tip(0.7707), it) }
    }

    @Test
    fun validationLargeNumber() = runTest {
        val largeNumber = validationTipProcessor.validation("7".repeat(56))

        largeNumber.foldError {
            println(it)
            assert(it.head is ValidationError.MaxLength)
        }
        assert(largeNumber.isLeft())
    }

    @Test
    fun validationNotANumber() = runTest {
        val notANumber = validationTipProcessor.validation("123TT4")
        notANumber.foldError {
            println(it.head)
            assert(it.head is ValidationError.NotATipNumber)
        }
        assert(notANumber.isLeft())
    }

    @Test
    fun validationNotANumberTow() = runTest {
        val notANumber = validationTipProcessor.validation("!!")
        notANumber.foldError {
            println(it.head)
            assert(it.head is ValidationError.NotATipNumber)
        }
        assert(notANumber.isLeft())
    }
}
