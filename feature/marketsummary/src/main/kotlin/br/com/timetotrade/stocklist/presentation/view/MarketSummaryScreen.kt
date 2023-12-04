package br.com.timetotrade.stocklist.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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

@Composable
fun MarketSummaryScreen(
    loading: Boolean = false,
    marketSummaryList: List<MarketSummary> = listOf(),
    listState: LazyListState = rememberLazyListState(),
) {
    ScreenLoading(show = loading)

    Column(
        Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {

        Text(
            text = "Market Summary",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                color = MaterialTheme.colorScheme.onBackground,
            ),
        )
        LazyColumn(
            state = listState,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(marketSummaryList) { stock ->
                SummaryItem(stock)
            }
        }
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
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(15.dp)
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
            Text(
                text = marketSummary.shortName,
                style = TextStyle(
                    fontSize = 21.sp,
                    fontWeight = FontWeight.W600,
                    color = MaterialTheme.colorScheme.background,
                ),
            )
            Text(
                text = marketSummary.fullExchangeName,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.background,
                ),
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text = marketSummary.regularMarketPreviousClose.fmt,
                style = TextStyle(
                    fontSize = 21.sp,
                    fontWeight = FontWeight.W600,
                    color = MaterialTheme.colorScheme.background,
                ),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreviewDark() {
    TimeToTradeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            MarketSummaryScreen(
                loading = false,
                marketSummaryList = createMock()
            )
        }
    }
}

private fun createMock(): List<MarketSummary> {
    return listOf(
        MarketSummary(
            symbol = "ES=F",
            fullExchangeName = "CME",
            exchange = "CME",
            shortName = "S&P Futures",
            regularMarketPreviousClose = RegularMarketValue(
                raw = 4400.0,
                fmt = "4,400.00",
            ),
        ),
        MarketSummary(
            symbol = "RTY=F",
            fullExchangeName = "CME",
            exchange = "CME",
            shortName = "Russell 2000 Futures",
            regularMarketPreviousClose = RegularMarketValue(
                raw = 4400.0,
                fmt = "4,400.00",
            )
        ),
        MarketSummary(
            symbol = "NQ=F",
            fullExchangeName = "CME",
            exchange = "CME",
            shortName = "Nasdaq Futures",
            regularMarketPreviousClose = RegularMarketValue(
                raw = 4400.0,
                fmt = "4,400.00",
            )
        ),
        MarketSummary(
            symbol = "YM=F",
            fullExchangeName = "CME",
            exchange = "CME",
            shortName = "Dow Futures",
            regularMarketPreviousClose = RegularMarketValue(
                raw = 4400.0,
                fmt = "4,400.00",
            ),
        ),
        MarketSummary(
            symbol = "CL=F",
            fullExchangeName = "CME",
            exchange = "CME",
            shortName = "Crude Oil Futures",
            regularMarketPreviousClose = RegularMarketValue(
                raw = 4400.0,
                fmt = "4,400.00",
            )
        ),
    )
}
