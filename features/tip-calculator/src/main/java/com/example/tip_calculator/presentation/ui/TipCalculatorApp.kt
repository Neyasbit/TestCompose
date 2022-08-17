package com.example.tip_calculator.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tip_calculator.presentation.TipCalcViewModel
import com.example.tip_calculator.presentation.TipCalcViewModelFactory


@Preview(showBackground = true)
@Composable
fun TipCalculatorApp(
    viewModel: TipCalcViewModel = viewModel(
        factory = TipCalcViewModelFactory()
    )
) {
    val viewState = viewModel.viewState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HeaderName()
        Spacer(modifier = Modifier.height(16.dp))
        EditNumberField(viewModel)
        ErrorEditNumber(viewState.value)
        Spacer(modifier = Modifier.height(16.dp))
        TipPercent(viewModel)
        Tip(viewState.value)
    }
}
