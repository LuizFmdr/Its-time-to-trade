package br.com.timetotrade.stocklist.presentation

import androidx.lifecycle.viewModelScope
import br.com.timetotrade.stocklist.domain.TradeStocksRepository
import br.com.timetotrade.stocklist.domain.model.Stock
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@HiltViewModel
class StockListViewModel @Inject constructor(
    private val repository: TradeStocksRepository
) : StateViewModel<StockListViewModel.StockListState>(StockListState()) {

    init {
        getStocks()
    }

    private fun getStocks() {
        viewModelScope.launch {
            repository.getStocks()
                .flowOn(Dispatchers.IO)
                .onStart { setState { copy(isLoading = true) } }
                .catch {
                    setState { copy(isLoading = false, errorMessage = it.message) }
                }
                .collect { stock ->
                    handleSuccess(stock)
                }
        }
    }

    private fun handleSuccess(stock: Stock) {
        setState {
            copy(
                isLoading = false,
                stocks = stocks.apply { add(stock) }
            )
        }
    }

    data class StockListState(
        val stocks: MutableList<Stock> = mutableListOf(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )
}

