package com.example.tip_calculator

import arrow.core.Option
import arrow.core.Some
import arrow.core.left
import com.example.tip_calculator.data.CalculateTip
import com.example.tip_calculator.data.ValidationError
import com.example.tip_calculator.data.ValidationTipProcessor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TipRepositoryTest {

    private val validationTipProcessor: ValidationTipProcessor = ValidationTipProcessor.Base()
    private val calculateTip: CalculateTip = CalculateTip.Base()

    private val repository: TipRepository = TipRepository.Base(
        calculateTip,
        validationTipProcessor
    )

    @Test
    fun testFetchNotANumber() = runTest {
        val tip = repository.fetchTip("Not a number")
        tip.foldError { Assert.assertEquals(ValidationError.NotATipNumber, it) }
    }

    @Test
    fun `provide a good number`() = runTest {
        val tip = repository.fetchTip("777")
        val actual = "$${(777 * 0.15)}"
        tip.foldSuccess { Assert.assertEquals(actual, it) }
    }

    @Test
    fun `provide symbols`()= runTest {
        val tip = repository.fetchTip(".")
        val actual = "$${(0.0)}"
        tip.foldSuccess { Assert.assertEquals(actual, it) }
    }

    @Test
    fun `provide symbols not valid`()= runTest {
        val tip = repository.fetchTip("/")
        tip.foldError { Assert.assertEquals(ValidationError.NotATipNumber, it) }
        val number: Option<Int> = Some(3)

    }
}