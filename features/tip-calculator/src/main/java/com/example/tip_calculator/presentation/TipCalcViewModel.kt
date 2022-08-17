package com.example.tip_calculator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.BaseViewModel
import com.example.core.DataEvent
import com.example.core.ErrorEvent
import com.example.core.Event
import com.example.core.UiEvent
import com.example.tip_calculator.TipRepository
import com.example.tip_calculator.data.CalculateTip
import com.example.tip_calculator.data.ValidationTipProcessor
import com.example.tip_calculator.fold
import kotlinx.coroutines.launch

data class TipCalcViewState(
    val amountInput: String = "",
    val tip: String = "",
    val tipPercent: Float = 15F,
    val errorMessage: String? = null
) {
    val isValidationError = errorMessage != null
    val isAmountInputNonBlankOrEmpty = amountInput.isNotEmpty() || amountInput.isNotBlank()
}

sealed class TipCalcDataEvent : DataEvent {
    class StartTipProcess(val amountInput: String, val percent: Float) : TipCalcDataEvent()
    class OnChangedBillAmount(val tip: String) : TipCalcDataEvent()
}

sealed class TipCalcUiEvent : UiEvent {
    class AmountInputData(val enteredUserAmount: String) : TipCalcUiEvent()
    class TipPercentInputData(val percent: Float) : TipCalcUiEvent()
}

sealed class TipErrorEvent : ErrorEvent {
    class OnTipError(override val error: Throwable) : TipErrorEvent()
}

class TipCalcViewModel(
    private val repository: TipRepository
) : BaseViewModel<TipCalcViewState>() {

    override fun initialViewState() = TipCalcViewState()

    override fun reduce(event: Event) = when (event) {
        is TipCalcUiEvent -> dispatchUiEvent(event)
        is TipCalcDataEvent -> dispatchDataEvent(event)
        else -> previousState
    }

    private fun dispatchDataEvent(dataEvent: TipCalcDataEvent) = when (dataEvent) {
        is TipCalcDataEvent.StartTipProcess -> {
            viewModelScope.launch {
                repository.fetchTip(dataEvent.amountInput, dataEvent.percent).fold(
                    onSuccess = { tip -> processDataEvent(TipCalcDataEvent.OnChangedBillAmount(tip)) },
                    onError = { processErrorEvent(TipErrorEvent.OnTipError(it)) }
                )
            }
            previousState
        }
        is TipCalcDataEvent.OnChangedBillAmount -> {
            previousState.copy(tip = dataEvent.tip, errorMessage = null)
        }
    }

    private fun dispatchUiEvent(uiEvent: TipCalcUiEvent) = when (uiEvent) {
        is TipCalcUiEvent.AmountInputData -> {
            processDataEvent(
                TipCalcDataEvent.StartTipProcess(
                    uiEvent.enteredUserAmount,
                    previousState.tipPercent
                )
            )
            previousState.copy(amountInput = uiEvent.enteredUserAmount)
        }
        is TipCalcUiEvent.TipPercentInputData -> {
            processDataEvent(
                TipCalcDataEvent.StartTipProcess(
                    previousState.amountInput,
                    uiEvent.percent
                )
            )
            previousState.copy(tipPercent = uiEvent.percent)
        }
    }


    override fun onHandleErrorEvent(event: ErrorEvent) =
        previousState.copy(tip = "", errorMessage = event.error.message)
}

class TipCalcViewModelFactory : ViewModelProvider.Factory {

    private val calculateTip: CalculateTip = CalculateTip.Base()
    private val validationTipProcessor: ValidationTipProcessor = ValidationTipProcessor.Base()

    private val repository: TipRepository = TipRepository.Base(
        calculateTip,
        validationTipProcessor
    )

    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TipCalcViewModel(repository) as T
    }
}