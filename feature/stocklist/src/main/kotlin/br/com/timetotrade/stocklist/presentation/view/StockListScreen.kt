package br.com.timetotrade.stocklist.presentation.view

import android.content.res.Configuration
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.timetotrade.desingsystem.TimeToTradeTheme
import br.com.timetotrade.stocklist.domain.model.Stock

@Composable
fun StockListScreen(
    loading: Boolean = false,
    stockList: List<Stock> = listOf(),
    listState: LazyListState = rememberLazyListState(),
) {
    ScreenLoading(show = loading)

    Column {
        //Search()

        LazyColumn(
            state = listState,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(stockList) { stock ->
                StockItem(stock)
            }

            item {
                ScreenLoading(show = loading)
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
                .padding(16.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun StockItem(stock: Stock) {
    Row(
        modifier = Modifier
            .aspectRatio(3.22f)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(15.dp)
            )
            .clip(shape = RoundedCornerShape(15.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stock.symbol,
                style = TextStyle(
                    fontSize = 21.sp,
                    fontWeight = FontWeight.W600,
                    color = MaterialTheme.colorScheme.onSurface,
                ),
            )
            Text(
                text = stock.enterpriseValue,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.onSurface,
                ),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    TimeToTradeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            StockListScreen(
                loading = false,
                stockList = createMock()
            )
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun DefaultPreviewDark() {
    TimeToTradeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            StockListScreen(
                loading = false,
                stockList = createMock()
            )
        }
    }
}

private fun createMock(): List<Stock> {
    return listOf(
        Stock("AAPL", "3.04T", 7.15),
        Stock("MSFT", "2.22T", 10.98),
        Stock("GOOG", "1.83T", 30.12),
        Stock("AMZN", "1.59T", 60.12),
        Stock("FB", "1.01T", 20.12),
    )
}
