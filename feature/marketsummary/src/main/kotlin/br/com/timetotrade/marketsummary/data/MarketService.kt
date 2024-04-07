package br.com.timetotrade.marketsummary.data

import br.com.timetotrade.marketsummary.data.model.CurrencyExchangeRateResponse
import br.com.timetotrade.marketsummary.data.model.SupportedCurrenciesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarketService {

    @GET("rates/latest")
    suspend fun getCurrencyExchangeRate(
        @Query("base") baseCurrency: String,
    ): CurrencyExchangeRateResponse

    @GET("supported-currencies")
    suspend fun getSupportedCurrencies(
    ): SupportedCurrenciesResponse
}
