package br.com.timetotrade.stocklist.data

import br.com.timetotrade.stocklist.data.model.MarketSummaryResponse
import br.com.timetotrade.stocklist.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarketService {

    @GET("market/v2/get-summary")
    suspend fun getMarketSummary(
        @Query("region") region: String,
    ): MarketSummaryResponse

    @GET("auto-complete")
    suspend fun search(
        @Query("q") query: String,
        @Query("region") region: String,
    ): SearchResponse
}
