package br.com.timetotrade.stocklist.domain

import br.com.timetotrade.stocklist.data.TradeDataSource
import br.com.timetotrade.stocklist.domain.model.Stock
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface TradeStocksRepository {

    fun getStocks(): Flow<Stock>
}

class TradeStocksRepositoryImpl @Inject constructor(
    private val dataSource: TradeDataSource
) : TradeStocksRepository {

    //TODO: screener selection will be implemented in the future
    override fun getStocks(): Flow<Stock> {
        return dataSource.getMarketScreener("trending")
            .map { response ->
                Stock(
                    symbol = response.meta.symbol,
                    enterpriseValue = response.body.enterpriseValue.fmt,
                    priceToEarnings = response.body.forwardPE.raw
                )
            }
    }
}
