package br.com.timetotrade.marketsummary.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SupportedCurrenciesResponse(
    @Json(name = "supportedCurrenciesMap")
    val supportedCurrenciesMap: Map<String, SupportedCurrencyResponse>
)

@JsonClass(generateAdapter = true)
data class SupportedCurrencyResponse(
    @Json(name = "availableFrom")
    val availableFrom: String,
    @Json(name = "availableUntil")
    val availableUntil: String,
    @Json(name = "countryCode")
    val countryCode: String?,
    @Json(name = "countryName")
    val countryName: String?,
    @Json(name = "currencyCode")
    val currencyCode: String,
    @Json(name = "currencyName")
    val currencyName: String?,
    @Json(name = "icon")
    val iconUrl: String,
    @Json(name = "status")
    val status: String
)
