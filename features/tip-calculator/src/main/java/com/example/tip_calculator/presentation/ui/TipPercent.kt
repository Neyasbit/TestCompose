package com.example.tip_calculator.presentation.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.example.tip_calculator.R
import com.example.tip_calculator.presentation.TipCalcUiEvent
import com.example.tip_calculator.presentation.TipCalcViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun TipPercent(viewModel: TipCalcViewModel) {
    val viewState by viewModel.viewState.collectAsState()

    Row {
        ModText(content = stringResource(id = R.string.tip))
        AnimatedContent(targetState = viewState.tipPercent,
            transitionSpec = {
                if (targetState > initialState) {
                      fadeIn() with fadeOut()
                } else {
                   fadeIn() with fadeOut()
                }.using(
                    SizeTransform(clip = false)
                )
            }) { targetTipPercent ->
            ModText(content = stringResource(id = R.string.tip_percent_title, targetTipPercent))
        }
    }


    SliderWithLabel(
        viewState.tipPercent,
        valueRange = 0f..50f
    ) {
        viewModel.processUiEvent(TipCalcUiEvent.TipPercentInputData(it))
    }
}