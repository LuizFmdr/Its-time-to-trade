package br.com.timetotrade.stocklist

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.timetotrade.stocklist.presentation.StockListViewModel
import br.com.timetotrade.stocklist.presentation.view.MarketSummaryScreen

@Composable
fun MarketSummaryRoute(viewModel: StockListViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    val listState = rememberLazyListState()

    MarketSummaryScreen(
        listState = listState,
        loading = state.isLoading,
        marketSummaryList = state.marketSummaryList
    )
}

