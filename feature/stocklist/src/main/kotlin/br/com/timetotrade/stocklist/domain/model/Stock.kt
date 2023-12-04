package br.com.timetotrade.stocklist.domain.model

data class Stock(
    val symbol: String,
    val enterpriseValue: String,
    val priceToEarnings: Double
)
