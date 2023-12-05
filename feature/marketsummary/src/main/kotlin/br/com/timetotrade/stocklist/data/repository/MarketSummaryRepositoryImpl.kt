package br.com.timetotrade.stocklist.data.repository

import br.com.timetotrade.stocklist.data.source.MarketSummaryDataSource
import br.com.timetotrade.stocklist.domain.MarketSummaryRepository
import br.com.timetotrade.stocklist.domain.model.MarketSummary
import br.com.timetotrade.stocklist.domain.model.RegularMarketValue
import br.com.timetotrade.stocklist.domain.model.Spark
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MarketSummaryRepositoryImpl @Inject constructor(
    private val dataSource: MarketSummaryDataSource
) : MarketSummaryRepository {

    override fun getMarketSummary(): Flow<List<MarketSummary>> {
        return dataSource.getMarketSummary().map {
            it.map { result ->
                val closeList = result.sparkResponse.close?.map { v -> v.toFloat() } ?: emptyList()

                MarketSummary(
                    symbol = result.symbol,
                    fullExchangeName = result.fullExchangeName,
                    exchange = result.exchange,
                    shortName = result.shortName,
                    regularMarketPreviousClose = RegularMarketValue(
                        raw = result.regularMarketPreviousClose.raw,
                        fmt = result.regularMarketPreviousClose.fmt
                    ),
                    spark = Spark(
                        closeZipList = closeList.zipWithNext(),
                        closeList = closeList,
                        max = closeList.maxOrNull() ?: 0F,
                        min = closeList.minOrNull() ?: 0F,
                    ),
                )
            }
        }
    }
}
