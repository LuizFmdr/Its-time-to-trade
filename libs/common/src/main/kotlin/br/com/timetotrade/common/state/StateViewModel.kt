package br.com.timetotrade.common.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class StateViewModel<UiState, UiAction>(initialState: UiState) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<UiState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<UiAction>()
    val action: SharedFlow<UiAction>
        get() = _action.asSharedFlow()

    protected val stateValue: UiState
        get() = state.value

    protected fun setState(block: UiState.() -> UiState) {
        async {
            _state.emit(_state.value.block())
        }
    }

    protected fun setState(state: UiState) {
        async {
            _state.emit(state)
        }
    }

    protected fun withState(block: UiState.() -> Unit) {
        block(stateValue)
    }

    protected fun sendAction(action: UiAction) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }

    protected inline fun async(crossinline block: suspend () -> Unit) {
        viewModelScope.launch {
            block()
        }
    }
}
