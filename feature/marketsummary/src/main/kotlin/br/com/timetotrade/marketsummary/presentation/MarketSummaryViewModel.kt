package br.com.timetotrade.marketsummary.presentation

import androidx.lifecycle.viewModelScope
import br.com.timetotrade.common.network.di.IoDispatcher
import br.com.timetotrade.common.state.StateViewModel
import br.com.timetotrade.marketsummary.data.model.SupportedCurrencyResponse
import br.com.timetotrade.marketsummary.domain.MarketSummaryRepository
import br.com.timetotrade.marketsummary.domain.model.AvailableMarket
import br.com.timetotrade.marketsummary.domain.model.CurrencyExchangeRate
import br.com.timetotrade.marketsummary.domain.model.MARKET_LIST
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.MarketSummaryUiAction
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.StockListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

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
    }

    private fun getStocks() {
        viewModelScope.launch(ioDispatcher) {
            repository.fetchSupportedCurrencies()
                .onStart { setState { copy(isLoading = true) } }
                .onEach { currencies ->
                    setState { copy(availableCurrencies = currencies) }
                }
                .catch {
                    Timber.e(it)
                    setState { copy(isLoading = false, errorMessage = it.message) }
                }
                .collect()
        }
    }

    private fun handleSuccess(marketSummary: CurrencyExchangeRate) {
        setState {
            copy(
                isLoading = false,
                marketSummaryList = marketSummary
            )
        }
    }

    /*    fun handleIntents(intent: MarketSummaryUiIntent) {
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
        }*/

    /*  private fun onMarketChange(intent: OnMarketChange) {
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
  */
    data class StockListState(
        val marketSummaryList: CurrencyExchangeRate? = null,
        val marketList: List<AvailableMarket> = MARKET_LIST,
        val availableCurrencies: List<SupportedCurrencyResponse> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

    sealed interface MarketSummaryUiAction {
        data object GoToSearch : MarketSummaryUiAction
    }

    sealed interface MarketSummaryUiIntent {
        data object OnMarketSelect : MarketSummaryUiIntent
        data class OnBackPress(val isBottomSheetVisible: Boolean) : MarketSummaryUiIntent
        data class OnMarketChange(val market: AvailableMarket) : MarketSummaryUiIntent
        data class OnSearchFocusChanged(val hasFocus: Boolean) : MarketSummaryUiIntent
    }
}
