package com.example.tip_calculator.presentation.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
internal fun ModText(content: String, modifier: Modifier = Modifier) {
    Text(
        text = content,
        fontSize = 16.sp,
        color = MaterialTheme.colors.primary,
        modifier = modifier
    )
}