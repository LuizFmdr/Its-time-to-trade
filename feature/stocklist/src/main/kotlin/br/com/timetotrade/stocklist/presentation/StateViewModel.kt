package br.com.timetotrade.stocklist.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class StateViewModel<UiState>(initialState: UiState) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<UiState> = _state

/*
    private val _action = MutableSharedFlow<UiAction>()
    val action: SharedFlow<UiAction> = _action

*/
    val stateValue: UiState
        get() = state.value

    protected fun setState(block: UiState.() -> UiState) {
        _state.value = _state.value.block()
    }
/*

    protected fun sendAction(action: UiAction) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }
*/
}
