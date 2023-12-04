package br.com.timetotrade.stocklist.domain

import br.com.timetotrade.stocklist.data.TradeDataSource
import br.com.timetotrade.stocklist.domain.model.MarketSummary
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface MarketSummaryRepository {

    fun getMarketSummary(): Flow<List<MarketSummary>>
}

class MarketSummaryRepositoryImpl @Inject constructor(
    private val dataSource: TradeDataSource
) : MarketSummaryRepository {

    override fun getMarketSummary(): Flow<List<MarketSummary>> {
        return dataSource.getMarketSummary()
            .map {
                it.map { result ->
                    MarketSummary(
                        symbol = result.symbol,
                        fullExchangeName = result.fullExchangeName,
                        exchange = result.exchange
                    )
                }
            }
    }
}
