package br.com.timetotrade.search.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel()
) {

    val searchState by viewModel.state.collectAsStateWithLifecycle()

    SearchScreen(
        searchState = searchState,
        onSearchTextChanged = viewModel::onSearchTextChanged
    )
}
