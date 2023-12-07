package br.com.timetotrade.search.presentation

import androidx.lifecycle.viewModelScope
import br.com.timetotrade.common.network.di.IoDispatcher
import br.com.timetotrade.common.state.StateViewModel
import br.com.timetotrade.search.domain.SearchRepository
import br.com.timetotrade.search.presentation.SearchViewModel.SearchState
import br.com.timetotrade.search.presentation.SearchViewModel.SearchUiAction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : StateViewModel<SearchState, SearchUiAction>(SearchState()) {

    private var searchJob: Job? = null

     fun onSearchTextChanged(searchText: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1000)
            searchStock(searchText)
        }
    }

    private fun searchStock(searchText: String) {
        viewModelScope.launch(ioDispatcher) {
            repository.search(searchText)
                .onStart {

                }
                .catch {

                }
                .collect {

                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }

    data class SearchState(
        val searchText: String = "",
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

    sealed interface SearchUiAction {

    }

    sealed interface MarketSummaryUiIntent {
        data class SearchStock(val searchText: String) : MarketSummaryUiIntent
        data class OnSearchFocusChanged(val hasFocus: Boolean) : MarketSummaryUiIntent
    }
}
