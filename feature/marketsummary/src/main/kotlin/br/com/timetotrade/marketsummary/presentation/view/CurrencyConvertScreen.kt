package br.com.timetotrade.marketsummary.presentation.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.SwapHoriz
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.timetotrade.desingsystem.DsString
import br.com.timetotrade.desingsystem.PrimaryDark
import br.com.timetotrade.desingsystem.PrimaryLight
import br.com.timetotrade.desingsystem.SecondaryLight
import br.com.timetotrade.desingsystem.TimeToTradeTheme
import br.com.timetotrade.marketsummary.domain.model.Currency
import br.com.timetotrade.marketsummary.domain.model.CurrencyExchangeRate
import br.com.timetotrade.marketsummary.domain.model.MARKET_LIST
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.StockListState
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import java.time.LocalDateTime
import kotlin.random.Random
import kotlinx.coroutines.Dispatchers

@Composable
fun CurrencyConvertScreen(
    loading: Boolean = false,
    state: StockListState,
    listState: LazyListState = rememberLazyListState(),
) {

    Scaffold { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            CurrencyCard(
                currency = Currency(
                    "US Dollar",
                    "USD",
                    1.0,
                    "https://flagsapi.com/US/flat/48.png"
                )
            )

            Icon(
                Icons.Outlined.SwapHoriz,
                contentDescription = null,
                tint = SecondaryLight,
                modifier = Modifier.size(24.dp),
            )

            CurrencyCard(
                currency = Currency(
                    "Brazilian Real",
                    "BRL",
                    5.0,
                    "https://flagsapi.com/BR/flat/48.png"
                )
            )
        }

        Column(
            Modifier
                .background(PrimaryDark)
                .padding(paddingValues),
        ) {
            ScreenLoading(show = loading)

            if (state.availableCurrencies.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(),
                    state = listState,
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(state.availableCurrencies, key = { it.currencyCode }) { currency ->
                        SummaryItem(
                            Modifier,
                            marketSummary = Currency(
                                code = currency.currencyCode,
                                value = Random.nextDouble(),
                                name = currency.currencyName.orEmpty(),
                                imgUrl = currency.iconUrl
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CurrencyCard(currency: Currency) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .height(24.dp),
            model = currency.imgUrl,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "${currency.code} - ${currency.name}",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                color = SecondaryLight,
            ),
        )
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
fun SummaryItem(modifier: Modifier, marketSummary: Currency) {
    val context = LocalContext.current
    val imageUrl = marketSummary.imgUrl
    val imageRequest = ImageRequest.Builder(context)
        .data(imageUrl)
        .dispatcher(Dispatchers.IO)
        .memoryCacheKey(imageUrl)
        .diskCacheKey(imageUrl)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryDark,
        ),
        border = BorderStroke(1.dp, PrimaryLight),
    ) {
        Row {
            AsyncImage(
                model = imageRequest,
                modifier = Modifier
                    .height(24.dp),
                contentDescription = null,
            )
            Text(
                text = marketSummary.value.toString(),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    color = SecondaryLight,
                ),
            )
        }
    }
}

@Composable
@Preview()
fun DefaultPreviewDark() {
    TimeToTradeTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CurrencyConvertScreen(
                loading = false,
                state = StockListState(
                    marketList = MARKET_LIST,
                    marketSummaryList = createMock(),
                ),
            )
        }
    }
}

private fun createMock(): CurrencyExchangeRate {
    return CurrencyExchangeRate(
        baseCurrencyCode = "USD",
        baseCurrencyUrl = "https://flagsapi.com/BE/flat/48.png",
        date = LocalDateTime.now(),
        rates = listOf(
            Currency(
                code = "BRL",
                value = 5.0,
                name = "Brazilian Real",
                imgUrl = "https://flagsapi.com/BR/flat/48.png"
            ),
        )
    )
}
