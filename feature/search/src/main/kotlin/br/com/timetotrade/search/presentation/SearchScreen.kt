package br.com.timetotrade.search.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.timetotrade.desingsystem.DsString
import br.com.timetotrade.desingsystem.PrimaryDark
import br.com.timetotrade.desingsystem.PrimaryLight
import br.com.timetotrade.desingsystem.TimeToTradeTheme
import br.com.timetotrade.search.presentation.SearchViewModel.SearchState

@Composable
fun SearchScreen(
    searchState: SearchState,
    onSearchTextChanged: (String) -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = PrimaryDark
    ) {
        Column {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .onFocusChanged {
                        if (it.isFocused) {

                        }
                    },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryLight
                ),
                value = searchState.searchText,
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
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {

                })
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreviewDark() {
    TimeToTradeTheme {
        SearchScreen(
            searchState = SearchState(),
            onSearchTextChanged = {}
        )
    }
}
