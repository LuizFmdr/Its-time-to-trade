package br.com.timetotrade.marketsummary.domain.model

import coil.request.ImageRequest
import java.time.Instant
import java.time.LocalDateTime

data class CurrencyExchangeRate(
    val baseCurrencyCode: String,
    val baseCurrencyUrl: String,
    val date: LocalDateTime,
    val rates: List<Currency>
)

data class Currency(
    val name: String,
    val code: String,
    val value: Double,
    val imgUrl: String
)
