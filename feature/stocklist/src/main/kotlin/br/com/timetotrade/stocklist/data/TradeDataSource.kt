package br.com.timetotrade.stocklist.data

import br.com.timetotrade.network.ResponseWrapper
import br.com.timetotrade.stocklist.data.model.StockStatisticsBodyResponse
import br.com.timetotrade.stocklist.data.model.StockStatisticsMetaResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

const val DEFAULT_STOCKS_LIST_SIZE = 5

//this api has a hard limit for monthly requests
const val MAX_REQUEST_COUNT = 10

interface TradeDataSource {

    fun getMarketScreener(screenerType: String): Flow<ResponseWrapper<StockStatisticsBodyResponse, StockStatisticsMetaResponse>>
}

class TradeDataSourceImpl(
    private val stocksService: StocksService
) : TradeDataSource {

    override fun getMarketScreener(screenerType: String): Flow<ResponseWrapper<StockStatisticsBodyResponse, StockStatisticsMetaResponse>> {
        return flow {
            val stocksList = stocksService.getMarketScreener(screenerType).body

            stocksList.forEachIndexed { index, symbol ->
                emit(stocksService.getStatistics(symbol))
                if (index == DEFAULT_STOCKS_LIST_SIZE)
                    delay(1000)

                if (index == MAX_REQUEST_COUNT)
                    return@forEachIndexed
            }
        }
    }
}
