package br.com.timetotrade.marketsummary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.timetotrade.desingsystem.SecondaryLight
import br.com.timetotrade.desingsystem.TertiaryDark
import br.com.timetotrade.desingsystem.TimeToTradeTheme
import br.com.timetotrade.marketsummary.domain.model.AvailableMarket
import br.com.timetotrade.marketsummary.domain.model.MARKET_LIST
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.MarketSummaryUiAction.GoToSearch
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.MarketSummaryUiIntent
import br.com.timetotrade.marketsummary.presentation.MarketSummaryViewModel.MarketSummaryUiIntent.OnMarketChange
import br.com.timetotrade.marketsummary.presentation.view.CurrencyConvertScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MarketSummaryRoute(
    goToSearch: () -> Unit = {},
    viewModel: MarketSummaryViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CurrencyConvertScreen(
        state = state,
    )

    ObserveActions(
        viewModel = viewModel,
        goToSearch = goToSearch
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
private fun BottomSheetContent(
    availableMarketList: List<AvailableMarket>,
    intentChannel: (MarketSummaryUiIntent) -> Unit = {},
) {
    FlowRow(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        maxItemsInEachRow = 4
    ) {
        availableMarketList.map { market ->
            FilterChip(
                modifier = Modifier.weight(1f),
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = TertiaryDark
                ),
                onClick = { intentChannel(OnMarketChange(market)) },
                label = {
                    Text(
                        market.code,
                        style = TextStyle(
                            color = SecondaryLight
                        ),
                    )
                },
                selected = market.isSelected,
                leadingIcon = {
                    if (market.isSelected) {
                        Icon(
                            imageVector = Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ObserveActions(
    viewModel: MarketSummaryViewModel,
    goToSearch: () -> Unit,
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.action.collect {
            when (it) {

                GoToSearch -> goToSearch()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreviewDark() {
    TimeToTradeTheme {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomSheetContent(
                availableMarketList = MARKET_LIST,
                intentChannel = {}
            )
        }
    }
}
