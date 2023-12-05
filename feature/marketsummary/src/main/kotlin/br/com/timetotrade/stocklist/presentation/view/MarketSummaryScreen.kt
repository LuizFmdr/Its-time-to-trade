package br.com.timetotrade.stocklist.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShowChart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.timetotrade.desingsystem.TimeToTradeTheme
import br.com.timetotrade.stocklist.domain.model.MarketSummary
import br.com.timetotrade.stocklist.domain.model.RegularMarketValue
import br.com.timetotrade.stocklist.domain.model.Spark

@Composable
fun MarketSummaryScreen(
    loading: Boolean = false,
    marketSummaryList: List<MarketSummary> = listOf(),
    listState: LazyListState = rememberLazyListState(),
) {
    ScreenLoading(show = loading)

    Column(
        Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                Header()
            }
            items(marketSummaryList) { stock ->
                SummaryItem(stock)
            }
        }
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Outlined.ShowChart,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.tertiary
        )
        Text(
            text = "Today's Stock Highlights", style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                color = MaterialTheme.colorScheme.tertiary,
            ), modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
        )
    }
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
fun SummaryItem(marketSummary: MarketSummary) {
    Row(
        modifier = Modifier
            .aspectRatio(3.22f)
            .background(
                color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(15.dp)
            )
            .clip(shape = RoundedCornerShape(15.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = marketSummary.shortName,
                    style = TextStyle(
                        fontSize = 21.sp,
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.tertiary,
                    ),
                )
                Text(
                    text = marketSummary.regularMarketPreviousClose.fmt,
                    style = TextStyle(
                        fontSize = 21.sp,
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.tertiary,
                    ),
                )
            }
            Text(
                text = marketSummary.fullExchangeName,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.tertiary,
                ),
            )
        }
        if (marketSummary.spark.closeList.isNotEmpty()) {
            LineChart(
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
                loading = false, marketSummaryList = createMock()
            )
        }
    }
}

private fun createMock(): List<MarketSummary> {
    return (0..10).map {
        listOf(
            MarketSummary(
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
