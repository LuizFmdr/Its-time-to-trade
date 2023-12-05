package br.com.timetotrade.stocklist.presentation

import androidx.lifecycle.viewModelScope
import br.com.timetotrade.stocklist.domain.MarketSummaryRepository
import br.com.timetotrade.stocklist.domain.model.MarketSummary
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class StockListViewModel @Inject constructor(
    private val repository: MarketSummaryRepository
) : StateViewModel<StockListViewModel.StockListState>(StockListState()) {

    init {
        getStocks()
    }

    private fun getStocks() {
        viewModelScope.launch {
            repository.getMarketSummary()
                .flowOn(Dispatchers.IO)
                .onStart { setState { copy(isLoading = true) } }
                .onEach { summary ->
                    handleSuccess(summary)
                }
                .catch {
                    Timber.e(it)
                    setState { copy(isLoading = false, errorMessage = it.message) }
                }
                .collect()
        }
    }

    private fun handleSuccess(marketSummary: List<MarketSummary>) {
        setState {
            copy(
                isLoading = false,
                marketSummaryList = marketSummary
            )
        }
    }

    data class StockListState(
        val marketSummaryList: List<MarketSummary> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )
}

