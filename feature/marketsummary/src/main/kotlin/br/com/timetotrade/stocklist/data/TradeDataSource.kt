package br.com.timetotrade.stocklist.data

import br.com.timetotrade.stocklist.data.model.MarketResult
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface TradeDataSource {

    fun getMarketSummary(): Flow<List<MarketResult>>
}

class TradeDataSourceImpl @Inject constructor(
    private val marketService: MarketService
) : TradeDataSource {

    override fun getMarketSummary(): Flow<List<MarketResult>> {
        return flow {
            while (true) {
                emit(marketService.getMarketSummary().marketSummaryAndSparkResponse.marketResultList)
                delay(8000)
            }
        }
    }
}
