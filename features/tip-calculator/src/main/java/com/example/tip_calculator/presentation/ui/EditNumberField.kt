package com.example.tip_calculator.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import com.example.tip_calculator.R
import com.example.tip_calculator.presentation.TipCalcUiEvent
import com.example.tip_calculator.presentation.TipCalcViewModel

@Composable
 internal fun EditNumberField(
    viewModel: TipCalcViewModel
) {

    val viewState by viewModel.viewState.collectAsState()

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.cost_of_service)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        value = viewState.amountInput,
        textStyle = TextStyle(color = MaterialTheme.colors.primary),
        onValueChange = { viewModel.processUiEvent(TipCalcUiEvent.AmountInputData(it)) },
        isError = viewState.isValidationError,
    )
}