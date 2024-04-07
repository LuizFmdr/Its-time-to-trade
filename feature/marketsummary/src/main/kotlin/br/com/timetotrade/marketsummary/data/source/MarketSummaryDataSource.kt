package br.com.timetotrade.marketsummary.data.source

import br.com.timetotrade.marketsummary.data.MarketService
import br.com.timetotrade.marketsummary.data.model.CurrencyExchangeRateResponse
import br.com.timetotrade.marketsummary.data.model.SupportedCurrencyResponse
import br.com.timetotrade.marketsummary.db.dao.SupportedCurrenciesDao
import br.com.timetotrade.marketsummary.db.model.asExternalModel
import br.com.timetotrade.marketsummary.db.model.toEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MarketSummaryDataSource {

    fun fetchCurrencyExchangeRate(baseCurrency: String): Flow<CurrencyExchangeRateResponse>

    fun fetchSupportedCurrencies(): Flow<List<SupportedCurrencyResponse>>
}

class MarketSummaryDataSourceImpl @Inject constructor(
    private val marketService: MarketService,
    private val supportedCurrenciesDao: SupportedCurrenciesDao
) : MarketSummaryDataSource {

    override fun fetchCurrencyExchangeRate(baseCurrency: String): Flow<CurrencyExchangeRateResponse> {
        return flow {
            emit(marketService.getCurrencyExchangeRate(baseCurrency))
        }
    }

    override fun fetchSupportedCurrencies(): Flow<List<SupportedCurrencyResponse>> {
        return flow {
            val entitiesList = supportedCurrenciesDao.getOneOffSupportedCurrenciesEntities()
            if (entitiesList.isEmpty()) {
                val response = marketService.getSupportedCurrencies()
                val currencyList = response.supportedCurrenciesMap.map {
                    it.value
                }
                supportedCurrenciesDao.upsertSupportedCurrencies((currencyList.map { it.toEntity() }))
                emit(currencyList)
            } else {
                emit(entitiesList.map { it.asExternalModel() })
            }
        }
    }
}
