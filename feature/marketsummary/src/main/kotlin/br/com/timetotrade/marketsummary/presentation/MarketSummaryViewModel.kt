package br.com.timetotrade.marketsummary.presentation

import androidx.lifecycle.viewModelScope
import br.com.timetotrade.common.network.di.IoDispatcher
import br.com.timetotrade.common.state.StateViewModel
import br.com.timetotrade.marketsummary.domain.MarketSummaryRepository
import br.com.timetotrade.marketsummary.domain.model.AvailableMarket
import br.com.timetotrade.marketsummary.domain.model.MARKET_LIST
import br.com.timetotrade.marketsummary.domain.model.MarketSummary
import br.com.timetotrade.marketsummary.domain.model.currentMarketCode
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.MarketSummaryUiAction
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.MarketSummaryUiAction.GoToSearch
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.MarketSummaryUiAction.HideMarketList
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.MarketSummaryUiAction.ShowMarketList
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.MarketSummaryUiIntent.OnBackPress
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.MarketSummaryUiIntent.OnMarketChange
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.MarketSummaryUiIntent.OnMarketSelect
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.MarketSummaryUiIntent.OnSearchFocusChanged
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.StockListState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class MarketSummaryViewModel @Inject constructor(
    private val repository: MarketSummaryRepository,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : StateViewModel<StockListState, MarketSummaryUiAction>(
    StockListState()
) {

    init {
        getStocks()
        validateMagicNumber(36)
    }

    fun validateMagicNumber(number: Int): Boolean {
        return number == 42
    }

    private fun getStocks() {
        viewModelScope.launch(ioDispatcher) {
            repository.getMarketSummary(stateValue.marketList.currentMarketCode)
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

            is OnMarketChange -> onMarketChange(intent)

            is OnBackPress -> {
                if (intent.isBottomSheetVisible) {
                    sendAction(HideMarketList)
                }
            }

            is OnSearchFocusChanged -> {
                if (intent.hasFocus) {
                    sendAction(GoToSearch)
                }
            }
        }
    }

    private fun onMarketChange(intent: OnMarketChange) {
        sendAction(HideMarketList)
        if (intent.market.code != stateValue.marketList.currentMarketCode) {
            setState {
                copy(
                    marketList = marketList.map {
                        it.copy(isSelected = it.code == intent.market.code)
                    }
                )
            }
            getStocks()
        }
    }

    data class StockListState(
        val marketSummaryList: List<MarketSummary> = emptyList(),
        val marketList: List<AvailableMarket> = MARKET_LIST,
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

    sealed interface MarketSummaryUiAction {
        data object ShowMarketList : MarketSummaryUiAction
        data object HideMarketList : MarketSummaryUiAction
        data object GoToSearch : MarketSummaryUiAction
    }

    sealed interface MarketSummaryUiIntent {
        data object OnMarketSelect : MarketSummaryUiIntent
        data class OnBackPress(val isBottomSheetVisible: Boolean) : MarketSummaryUiIntent
        data class OnMarketChange(val market: AvailableMarket) : MarketSummaryUiIntent
        data class OnSearchFocusChanged(val hasFocus: Boolean) : MarketSummaryUiIntent
    }
}
