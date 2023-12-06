package br.com.timetotrade.stocklist.presentation

import androidx.lifecycle.viewModelScope
import br.com.timetotrade.stocklist.domain.MarketSummaryRepository
import br.com.timetotrade.stocklist.domain.model.AvailableMarket
import br.com.timetotrade.stocklist.domain.model.MARKET_LIST
import br.com.timetotrade.stocklist.domain.model.MarketSummary
import br.com.timetotrade.stocklist.domain.model.selectedMarket
import br.com.timetotrade.stocklist.presentation.MarketSummaryViewModel.MarketSummaryUiAction
import br.com.timetotrade.stocklist.presentation.MarketSummaryViewModel.MarketSummaryUiAction.HideMarketList
import br.com.timetotrade.stocklist.presentation.MarketSummaryViewModel.MarketSummaryUiAction.ShowMarketList
import br.com.timetotrade.stocklist.presentation.MarketSummaryViewModel.MarketSummaryUiIntent.OnMarketSelect
import br.com.timetotrade.stocklist.presentation.MarketSummaryViewModel.MarketSummaryUiIntent.OnMarketSelected
import br.com.timetotrade.stocklist.presentation.MarketSummaryViewModel.StockListState
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
class MarketSummaryViewModel @Inject constructor(
    private val repository: MarketSummaryRepository
) : StateViewModel<StockListState, MarketSummaryUiAction>(
    StockListState()
) {

    init {
        getStocks()
    }

    private fun getStocks() {
        viewModelScope.launch {
            repository.getMarketSummary(stateValue.availableMarketList.selectedMarket)
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

    fun handleIntents(intent: MarketSummaryUiIntent) {
        when (intent) {
            OnMarketSelect -> {
                sendAction(ShowMarketList)
            }

            is OnMarketSelected -> {
                sendAction(HideMarketList)
            }
        }
    }

    data class StockListState(
        val marketSummaryList: List<MarketSummary> = emptyList(),
        val availableMarketList: List<AvailableMarket> = MARKET_LIST,
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

    sealed interface MarketSummaryUiAction {
        data object ShowMarketList : MarketSummaryUiAction
        data object HideMarketList : MarketSummaryUiAction
    }

    sealed interface MarketSummaryUiIntent {
        data object OnMarketSelect : MarketSummaryUiIntent

        data class OnMarketSelected(val market: AvailableMarket) : MarketSummaryUiIntent
    }
}
