package br.com.timetotrade.stocklist.data.source

import br.com.timetotrade.stocklist.data.MarketService
import br.com.timetotrade.stocklist.data.model.MarketResult
import br.com.timetotrade.stocklist.data.model.QuoteResponse
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//As was requested, the update interval was set as 8 seconds
const val UPDATE_INTERVAL = 8000L

interface MarketSummaryDataSource {

    fun getMarketSummary(selectedMarket: String): Flow<List<MarketResult>>

    fun search(query: String, selectedMarket: String): Flow<List<QuoteResponse>>
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

    override fun search(query: String, selectedMarket: String): Flow<List<QuoteResponse>> {
        return flow {
            emit(marketService.search(query, selectedMarket).quoteResponses)
        }
    }
}
