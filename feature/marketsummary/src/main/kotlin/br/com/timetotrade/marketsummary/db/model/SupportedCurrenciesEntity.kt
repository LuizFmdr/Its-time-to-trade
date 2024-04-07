package br.com.timetotrade.marketsummary.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.timetotrade.marketsummary.data.model.SupportedCurrenciesResponse
import br.com.timetotrade.marketsummary.data.model.SupportedCurrencyResponse

@Entity(
    tableName = "supported_currencies",
)
data class SupportedCurrenciesEntity(
    @PrimaryKey
    val currencyCode: String,
    val availableFrom: String,
    val availableUntil: String,
    val countryCode: String,
    val countryName: String,
    val currencyName: String,
    val iconUrl: String,
    val status: String
)

fun SupportedCurrenciesEntity.asExternalModel() = SupportedCurrencyResponse(
    availableFrom = availableFrom,
    availableUntil = availableUntil,
    countryCode = countryCode,
    countryName = countryName,
    currencyCode = currencyCode,
    currencyName = currencyName,
    iconUrl = iconUrl,
    status = status
)

fun SupportedCurrencyResponse.toEntity() = SupportedCurrenciesEntity(
    availableFrom = availableFrom,
    availableUntil = availableUntil,
    countryCode = countryCode.orEmpty(),
    countryName = countryName.orEmpty(),
    currencyCode = currencyCode,
    currencyName = currencyName.orEmpty(),
    iconUrl = iconUrl,
    status = status
)
