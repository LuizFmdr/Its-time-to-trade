package br.com.timetotrade.stocklist

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.timetotrade.stocklist.presentation.MarketSummaryViewModel
import br.com.timetotrade.stocklist.presentation.MarketSummaryViewModel.MarketSummaryUiAction.HideMarketList
import br.com.timetotrade.stocklist.presentation.MarketSummaryViewModel.MarketSummaryUiAction.ShowMarketList
import br.com.timetotrade.stocklist.presentation.MarketSummaryViewModel.MarketSummaryUiIntent.OnMarketSelected
import br.com.timetotrade.stocklist.presentation.view.MarketSummaryScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MarketSummaryRoute(viewModel: MarketSummaryViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()
    val bottomSheetState =
        androidx.compose.material.rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(sheetState = bottomSheetState, sheetContent = {
        LazyHorizontalGrid(rows = GridCells.Fixed(3)) {
            this.items(state.availableMarketList) { market ->
                FilterChip(
                    onClick = { viewModel.handleIntents(OnMarketSelected(market)) },
                    label = {
                        Text(market.code)
                    },
                    selected = market.isSelected,
                    leadingIcon = {
                        if (market.isSelected) {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    }
                )
            }
        }
    }) {
        MarketSummaryScreen(
            listState = listState,
            loading = state.isLoading,
            marketSummaryList = state.marketSummaryList,
            intentChannel = viewModel::handleIntents
        )
    }

    ObserveActions(
        viewModel = viewModel,
        bottomSheetState = bottomSheetState
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ObserveActions(
    viewModel: MarketSummaryViewModel,
    bottomSheetState: ModalBottomSheetState,
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.action.collect {
            when (it) {
                ShowMarketList -> {
                    scope.launch { bottomSheetState.show() }
                }

                HideMarketList -> {
                    scope.launch { bottomSheetState.hide() }
                }
            }
        }
    }
}
