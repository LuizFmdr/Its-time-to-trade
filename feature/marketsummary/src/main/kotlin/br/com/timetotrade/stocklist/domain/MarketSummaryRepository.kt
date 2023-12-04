package br.com.timetotrade.stocklist.domain

import br.com.timetotrade.stocklist.data.MarketSummaryDataSource
import br.com.timetotrade.stocklist.domain.model.MarketSummary
import br.com.timetotrade.stocklist.domain.model.RegularMarketValue
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface MarketSummaryRepository {

    fun getMarketSummary(): Flow<List<MarketSummary>>
}

class MarketSummaryRepositoryImpl @Inject constructor(
    private val dataSource: MarketSummaryDataSource
) : MarketSummaryRepository {

    override fun getMarketSummary(): Flow<List<MarketSummary>> {
        return dataSource.getMarketSummary().map {
                it.map { result ->
                    MarketSummary(
                        symbol = result.symbol,
                        fullExchangeName = result.fullExchangeName,
                        exchange = result.exchange,
                        shortName = result.shortName,
                        regularMarketPreviousClose = RegularMarketValue(
                            raw = result.regularMarketPreviousClose.raw,
                            fmt = result.regularMarketPreviousClose.fmt
                        )
                    )
                }
            }
    }
}
