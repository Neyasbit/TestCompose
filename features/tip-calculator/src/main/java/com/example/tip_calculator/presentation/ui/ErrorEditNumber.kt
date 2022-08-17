package com.example.tip_calculator.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.example.tip_calculator.presentation.TipCalcViewState

@Composable
internal fun ColumnScope.ErrorEditNumber(viewState: TipCalcViewState) {

    AnimatedVisibility(visible = viewState.isValidationError) {
        viewState.errorMessage?.let {
            Text(
                text = it,
                fontSize = 16.sp,
                color = MaterialTheme.colors.error
            )
        }
    }
}