package br.com.timetotrade.stocklist

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.timetotrade.stocklist.presentation.StockListViewModel
import br.com.timetotrade.stocklist.presentation.view.StockListScreen

@Composable
fun StockListRoute(viewModel: StockListViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    val listState = rememberLazyListState()

    StockListScreen(
        listState = listState,
        loading = state.isLoading,
        stockList = state.stocks,
    )
}

