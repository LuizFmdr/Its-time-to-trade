package br.com.timetotrade.search.navigation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.timetotrade.desingsystem.DsString
import br.com.timetotrade.desingsystem.PrimaryDark
import br.com.timetotrade.desingsystem.PrimaryLight

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
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {

                    })
                )
            }
        }
    }
}
