package br.com.timetotrade

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.timetotrade.desingsystem.PrimaryDark
import br.com.timetotrade.desingsystem.TimeToTradeTheme
import br.com.timetotrade.navigation.AppNavHost

@Composable
fun TimeToTradeApp() {
    TimeToTradeTheme {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = PrimaryDark
        ) {
            AppNavHost()
        }
    }
}
