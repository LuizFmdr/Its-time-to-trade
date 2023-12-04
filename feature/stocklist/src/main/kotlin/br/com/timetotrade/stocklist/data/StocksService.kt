package br.com.timetotrade.stocklist.data

import br.com.timetotrade.network.ResponseWrapper
import br.com.timetotrade.stocklist.data.model.MetaScreenerResponse
import br.com.timetotrade.stocklist.data.model.StockStatisticsBodyResponse
import br.com.timetotrade.stocklist.data.model.StockStatisticsMetaResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StocksService {

    @GET("v1/markets/screener")
    suspend fun getMarketScreener(
        @Query("list") screener: String
    ): ResponseWrapper<List<String>, MetaScreenerResponse>

/*    @GET("v1/markets/stock/modules?ticker={symbol}&module=asset-profile")
    suspend fun getProfile(
        @Path("symbol") symbol: String
    ): FinanceResponseWrapper<List<String>, Meta>*/

    @GET("v1/markets/stock/modules?ticker={symbol}&module=asset-statistics")
    suspend fun getStatistics(
        @Path("symbol") symbol: String
    ): ResponseWrapper<StockStatisticsBodyResponse, StockStatisticsMetaResponse>
/*
    @GET("v1/markets/stock/history?symbol={symbol}&interval={interval}&diffandsplits=false")
    suspend fun searchStock(
        @Path("symbol") symbol: String,
        @Path("interval") interval: HistoryAllowedIntervals,
    ): FinanceResponseWrapper<List<String>, Meta>*/
}
