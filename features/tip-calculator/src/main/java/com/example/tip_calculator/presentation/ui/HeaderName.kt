package com.example.tip_calculator.presentation.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.tip_calculator.R

@Composable
internal fun ColumnScope.HeaderName() {
    Text(
        text = stringResource(id = R.string.calculate_tip),
        fontSize = 16.sp,
        modifier = Modifier.align(Alignment.CenterHorizontally),
        color = MaterialTheme.colors.primary
    )
}