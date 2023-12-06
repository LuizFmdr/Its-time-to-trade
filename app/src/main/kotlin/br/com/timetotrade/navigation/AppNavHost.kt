package br.com.timetotrade.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.timetotrade.marketsummary.marketSummaryRoute
import br.com.timetotrade.marketsummary.marketSummaryScreen
import br.com.timetotrade.search.navigation.navigateSearch
import br.com.timetotrade.search.navigation.searchScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = marketSummaryRoute,
    ) {
        marketSummaryScreen(navController::navigateSearch)
        searchScreen()
    }
}
