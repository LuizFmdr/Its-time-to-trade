package br.com.timetotrade.marketsummary.domain

import br.com.timetotrade.marketsummary.data.model.SupportedCurrencyResponse
import br.com.timetotrade.marketsummary.domain.model.CurrencyExchangeRate
import kotlinx.coroutines.flow.Flow

interface MarketSummaryRepository {

    fun fetchCurrencyExchangeRate(baseCurrency: String): Flow<CurrencyExchangeRate>

    fun fetchSupportedCurrencies(): Flow<List<SupportedCurrencyResponse>>
}
