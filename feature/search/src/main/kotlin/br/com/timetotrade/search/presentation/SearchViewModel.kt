package br.com.timetotrade.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.timetotrade.search.domain.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private var searchJob: Job? = null

    private fun searchDebounced(searchText: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1000)
            searchStock(searchText)
        }
    }

    private fun searchStock(searchText: String) {
        viewModelScope.launch {
            repository.search(searchText)
                .flowOn(Dispatchers.IO)
                .onStart { }
                .catch { }
                .collect {

                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }

    data class SearchState(
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
