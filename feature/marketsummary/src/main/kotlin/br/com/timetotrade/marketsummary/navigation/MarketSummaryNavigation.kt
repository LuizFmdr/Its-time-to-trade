package br.com.timetotrade.marketsummary.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.timetotrade.marketsummary.MarketSummaryRoute

const val marketSummaryRoute = "marketSummary"

fun NavGraphBuilder.marketSummaryScreen(onSearchClick: () -> Unit) {

    composable(
        route = marketSummaryRoute,
    ) {
        MarketSummaryRoute(onSearchClick)
    }
}
