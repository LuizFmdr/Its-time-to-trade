package br.com.timetotrade.marketsummary.data.repository

import br.com.timetotrade.marketsummary.data.source.MarketSummaryDataSource
import br.com.timetotrade.marketsummary.domain.MarketSummaryRepository
import br.com.timetotrade.marketsummary.domain.model.MarketSummary
import br.com.timetotrade.marketsummary.domain.model.RegularMarketValue
import br.com.timetotrade.marketsummary.domain.model.Spark
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MarketSummaryRepositoryImpl @Inject constructor(
    private val dataSource: MarketSummaryDataSource
) : MarketSummaryRepository {

    override fun getMarketSummary(selectedMarket: String): Flow<List<MarketSummary>> {
        return dataSource.getMarketSummary(selectedMarket).map {
            it.map { result ->
                val closeList = result.sparkResponse.close?.map { v -> v.toFloat() } ?: emptyList()
                val lastClose = result.sparkResponse.close?.lastOrNull() ?: 0.0
                MarketSummary(
                    formattedName = "${result.shortName} (${result.symbol})",
                    currentPoints = lastClose,
                    todayComparedToPreviousClose = lastClose - result.regularMarketPreviousClose.raw,
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
