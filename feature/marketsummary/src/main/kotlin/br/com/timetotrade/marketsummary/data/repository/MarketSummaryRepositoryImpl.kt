package br.com.timetotrade.marketsummary.data.repository

import br.com.timetotrade.marketsummary.data.model.SupportedCurrencyResponse
import br.com.timetotrade.marketsummary.data.source.MarketSummaryDataSource
import br.com.timetotrade.marketsummary.domain.MarketSummaryRepository
import br.com.timetotrade.marketsummary.domain.model.Currency
import br.com.timetotrade.marketsummary.domain.model.CurrencyExchangeRate
import java.time.LocalDateTime
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val BASE_CURRENCY_IMAGE_URL = "https://currencyfreaks.com/photos/flags/"
private const val IMAGE_EXTENSION = ".png"

class MarketSummaryRepositoryImpl @Inject constructor(
    private val dataSource: MarketSummaryDataSource,
) : MarketSummaryRepository {

    override fun fetchCurrencyExchangeRate(baseCurrency: String): Flow<CurrencyExchangeRate> {
        return dataSource.fetchCurrencyExchangeRate(baseCurrency)
            .map { response ->
                CurrencyExchangeRate(
                    baseCurrencyCode = response.baseCurrency,
                    baseCurrencyUrl = buildCurrencyImageUrl(response.baseCurrency),
                    date = LocalDateTime.now(),
                    rates = response.rates.map { (code, value) ->
                        Currency(
                            code = code,
                            value = value.toDouble(),
                            imgUrl = buildCurrencyImageUrl(code),
                            name = code
                        )
                    }
                )
            }
    }

    override fun fetchSupportedCurrencies(): Flow<List<SupportedCurrencyResponse>> {
        return dataSource.fetchSupportedCurrencies()
    }

    private fun buildCurrencyImageUrl(currencyCode: String): String {
        return BASE_CURRENCY_IMAGE_URL + currencyCode.lowercase(Locale.getDefault()) + IMAGE_EXTENSION
    }
}
