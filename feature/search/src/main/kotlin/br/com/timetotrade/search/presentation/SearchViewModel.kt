package br.com.timetotrade.search.presentation

import androidx.lifecycle.viewModelScope
import br.com.timetotrade.common.network.di.IoDispatcher
import br.com.timetotrade.common.state.StateViewModel
import br.com.timetotrade.search.domain.SearchRepository
import br.com.timetotrade.search.domain.model.SearchResult
import br.com.timetotrade.search.presentation.SearchViewModel.SearchState
import br.com.timetotrade.search.presentation.SearchViewModel.SearchUiAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : StateViewModel<SearchState, SearchUiAction>(SearchState()) {

    private var searchJob: Job? = null

    fun onSearchTextChanged(searchText: String) {
        setState { copy(searchText = searchText) }
        if (searchText.isNotEmpty()) {
            searchJob?.cancel()
            searchJob = viewModelScope.launch {
                delay(600)
                searchStock(searchText)
            }
        }
    }

    private fun searchStock(searchText: String) {
        viewModelScope.launch(ioDispatcher) {
            repository.search(searchText)
                .catch {
                    Timber.e("Search catch", it)
                    setState {
                        copy(
                            resultList = emptyList(),
                            hasAnyResult = false
                        )
                    }
                }
                .collect {
                    setState {
                        copy(
                            resultList = it,
                            hasAnyResult = it.isNotEmpty() && searchText.isNotEmpty()
                        )
                    }
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }

    data class SearchState(
        val searchText: String = "",
        val resultList: List<SearchResult> = emptyList(),
        val hasAnyResult: Boolean = false,
    )

    sealed interface SearchUiAction
}
