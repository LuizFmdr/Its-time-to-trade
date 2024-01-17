package br.com.timetotrade.marketsummary.data.source

import br.com.timetotrade.marketsummary.data.MarketService
import br.com.timetotrade.marketsummary.data.model.MarketResult
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//As was requested, the update interval was set as 8 seconds
const val UPDATE_INTERVAL = 8000L

interface MarketSummaryDataSource {

    fun getMarketSummary(selectedMarket: String): Flow<List<MarketResult>>
}

class MarketSummaryDataSourceImpl @Inject constructor(
    private val marketService: MarketService
) : MarketSummaryDataSource {

    override fun getMarketSummary(selectedMarket: String): Flow<List<MarketResult>> {
        return flow {
            while (true) {
                emit(marketService.getMarketSummary(selectedMarket).marketSummaryAndSparkResponse.marketResultList)
                delay(UPDATE_INTERVAL)
            }
        }
    }
}
