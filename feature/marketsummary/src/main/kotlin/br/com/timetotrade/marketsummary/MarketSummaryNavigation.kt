package br.com.timetotrade.marketsummary

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val marketSummaryRoute = "marketSummary"

fun NavGraphBuilder.marketSummaryScreen(onSearchClick: () -> Unit) {

    composable(
        route = marketSummaryRoute,
    ) {
        MarketSummaryRoute(onSearchClick)
    }
}
