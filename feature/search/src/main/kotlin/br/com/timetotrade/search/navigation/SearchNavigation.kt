package br.com.timetotrade.search.navigation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.timetotrade.search.presentation.SearchRoute

const val SEARCH_ROUTE = "search"

fun NavController.navigateSearch() {
    this.navigate(SEARCH_ROUTE)
}

fun NavGraphBuilder.searchScreen() {
    composable(
        route = SEARCH_ROUTE,
        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            )
        }) {
        SearchRoute()
    }
}
