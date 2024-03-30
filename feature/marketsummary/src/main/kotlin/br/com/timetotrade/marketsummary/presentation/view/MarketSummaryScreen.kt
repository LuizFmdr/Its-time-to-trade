package br.com.timetotrade.marketsummary.presentation.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.timetotrade.desingsystem.DsString
import br.com.timetotrade.desingsystem.PrimaryDark
import br.com.timetotrade.desingsystem.PrimaryHighlight
import br.com.timetotrade.desingsystem.PrimaryLight
import br.com.timetotrade.desingsystem.SecondaryLight
import br.com.timetotrade.desingsystem.TimeToTradeTheme
import br.com.timetotrade.feature.market.summary.R
import br.com.timetotrade.marketsummary.domain.model.MARKET_LIST
import br.com.timetotrade.marketsummary.domain.model.MarketSummary
import br.com.timetotrade.marketsummary.domain.model.RegularMarketValue
import br.com.timetotrade.marketsummary.domain.model.Spark
import br.com.timetotrade.marketsummary.domain.model.currentMarketCode
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.MarketSummaryUiIntent
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.MarketSummaryUiIntent.OnMarketSelect
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.MarketSummaryUiIntent.OnSearchFocusChanged
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.StockListState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MarketSummaryScreen(
    loading: Boolean = false,
    state: StockListState,
    listState: LazyListState = rememberLazyListState(),
    intentChannel: (MarketSummaryUiIntent) -> Unit = {},
) {
    val expandedFabState by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0
        }
    }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                expanded = expandedFabState,
                containerColor = PrimaryLight,
                onClick = { intentChannel(OnMarketSelect) },
                icon = { Icon(Icons.Filled.ShowChart, "Extended floating action button.") },
                text = {
                    Text(
                        text = stringResource(
                            R.string.summary_fab_text,
                            state.marketList.currentMarketCode
                        )
                    )
                },
            )
        },
        topBar = {
            Search {
                intentChannel(OnSearchFocusChanged(it))
            }
        },
    ) { paddingValues ->
        Column(
            Modifier
                .background(PrimaryDark)
                .padding(paddingValues),
        ) {
            ScreenLoading(show = loading)
            LazyColumn(
                modifier = Modifier.fillMaxHeight(),
                state = listState,
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(state.marketSummaryList, key = { it.formattedName }) { market ->
                    SummaryItem(
                        Modifier.animateItemPlacement(),
                        marketSummary = market
                    )
                }
            }
        }
    }
}

@Composable
fun Search(onFocusChange: (Boolean) -> Unit) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .onFocusChanged {
                onFocusChange(it.isFocused)
            },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryLight
        ),
        value = "",
        placeholder = { Text(text = stringResource(id = DsString.find_stocks)) },
        trailingIcon = {
            Icon(
                modifier = Modifier.padding(start = 6.dp),
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        onValueChange = {},
        shape = CircleShape,
    )
}

@Composable
fun ScreenLoading(show: Boolean = false) {
    if (show) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun SummaryItem(modifier: Modifier, marketSummary: MarketSummary) {
    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryDark,
        ),
        border = BorderStroke(1.dp, PrimaryLight),
    ) {
        Row {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.Top,
            ) {
                Text(
                    text = marketSummary.formattedName,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        color = SecondaryLight,
                    ),
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = marketSummary.currentPoints.toString(),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W400,
                            color = SecondaryLight
                        ),
                    )
                    Text(
                        text = marketSummary.todayComparedToPreviousClose.toString(),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W400,
                            color = PrimaryHighlight
                        ),
                    )
                }
            }
        }
        if (marketSummary.spark.closeList.isNotEmpty()) {
            LineChart(
                Modifier.height(50.dp),
                spark = marketSummary.spark,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreviewDark() {
    TimeToTradeTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            MarketSummaryScreen(
                loading = false,
                state = StockListState(
                    marketList = MARKET_LIST,
                    marketSummaryList = createMock(),
                ),
            )
        }
    }
}

private fun createMock(): List<MarketSummary> {
    return (0..10).map {
        listOf(
            MarketSummary(
                formattedName = "BOVESPA (^BVSP)",
                currentPoints = 127.103,
                todayComparedToPreviousClose = 299.24,
                symbol = "ES=F",
                fullExchangeName = "CME",
                exchange = "CME",
                shortName = "S&P Futures",
                regularMarketPreviousClose = RegularMarketValue(
                    raw = 4400.0,
                    fmt = "4,400.00",
                ),
                spark = Spark(
                    closeList = listOf(
                        45.66F,
                        45.66F,
                        46.08F,
                        50.66F,
                    ),
                    closeZipList = listOf(
                        45.66F to 45.66F,
                        45.66F to 46.08F,
                        46.08F to 50.66F,
                    ),
                    max = 50.66F,
                    min = 45.66F,
                )
            )
        )
    }.flatten()
}
