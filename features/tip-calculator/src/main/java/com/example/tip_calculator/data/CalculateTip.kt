package com.example.tip_calculator.data

import java.text.NumberFormat

interface CalculateTip {

    companion object {
        const val DEFAULT_TIP_PERCENT = 15F
    }

    fun calculate(inputAmountValue: Double, tipPercent: Float): String

    class Base : CalculateTip {

        override fun calculate(inputAmountValue: Double, tipPercent: Float): String {
            val tip = inputAmountValue * tipPercent.toPercent
            return NumberFormat.getCurrencyInstance().format(tip)
        }

        private val Float.toPercent get() = this / 100
    }
}



