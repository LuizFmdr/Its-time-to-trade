package br.com.timetotrade.search.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.timetotrade.desingsystem.DsString
import br.com.timetotrade.desingsystem.PrimaryDark
import br.com.timetotrade.desingsystem.PrimaryHighlight
import br.com.timetotrade.desingsystem.PrimaryLight
import br.com.timetotrade.desingsystem.SecondaryLight
import br.com.timetotrade.desingsystem.TimeToTradeTheme
import br.com.timetotrade.search.domain.model.SearchResult
import br.com.timetotrade.search.presentation.SearchViewModel.SearchState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    searchState: SearchState,
    onSearchTextChanged: (String) -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = PrimaryDark,
        topBar = {
            Search(searchState.searchText, onSearchTextChanged)
        }
    ) { paddingValues ->
        LazyColumn(
            Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(searchState.resultList, key = { it.hashCode() }) { item ->
                CardItem(
                    Modifier.animateItemPlacement(),
                    item
                )
            }
        }
    }
}

@Composable
private fun CardItem(modifier: Modifier, item: SearchResult) {
    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryDark,
        ),
        border = BorderStroke(1.dp, PrimaryLight),
    ) {
        Column(
            Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.symbol,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        color = PrimaryHighlight,
                    ),
                )
                Text(
                    text = item.exchange,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        color = PrimaryHighlight,
                    ),
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.longName,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = SecondaryLight,
                    ),
                )
                Text(
                    text = item.exchDisp,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = SecondaryLight,
                    ),
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Search(searchText: String, onSearchTextChanged: (String) -> Unit) {
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    LaunchedEffect(true) {
        focusRequester.requestFocus()
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .focusRequester(focusRequester),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryLight,
            cursorColor = PrimaryHighlight,
        ),
        value = searchText,
        placeholder = { Text(text = stringResource(id = DsString.find_stocks)) },
        trailingIcon = {
            Icon(
                modifier = Modifier.padding(start = 6.dp),
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        onValueChange = onSearchTextChanged,
        shape = CircleShape,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            keyboard?.hide()
        })
    )
}

@Composable
@Preview(showBackground = true)
fun DefaultPreviewDark() {
    TimeToTradeTheme {
        SearchScreen(
            searchState = SearchState(
                searchText = "PETR3",
                hasAnyResult = true,
                resultList = (0..30).map {
                    listOf(
                        br.com.timetotrade.search.domain.model.SearchResult(
                            shortName = "Uber Technologies Inc",
                            longName = "Uber Technologies Inc",
                            symbol = "UBER",
                            exchDisp = "NYSE",
                            industry = "Software - Infrastructure",
                            exchange = "NYQ",
                            score = 157079.0,
                        )
                    )
                }.flatten()
            ),
            onSearchTextChanged = {}
        )
    }
}
