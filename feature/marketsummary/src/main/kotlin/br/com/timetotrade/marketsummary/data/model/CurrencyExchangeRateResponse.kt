package br.com.timetotrade.marketsummary.data.model
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class CurrencyExchangeRateResponse(
    @Json(name = "base")
    val baseCurrency: String,
    @Json(name = "date")
    val date: String,
    @Json(name = "rates")
    val rates: Map<String, String>
)
