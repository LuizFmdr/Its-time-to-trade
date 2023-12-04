package br.com.timetotrade

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import br.com.timetotrade.desingsystem.TimeToTradeTheme
import br.com.timetotrade.navigation.AppNavHost

@Composable
fun TimeToTradeApp() {
    TimeToTradeTheme {
        val navController = rememberNavController()

        Scaffold { padding ->
            Box(modifier = Modifier.fillMaxSize()) {
                AppNavHost(
                    navController = navController,
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}
