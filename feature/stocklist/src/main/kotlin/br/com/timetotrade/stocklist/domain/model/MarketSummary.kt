package br.com.timetotrade.stocklist.domain.model

data class MarketSummary(
    val symbol: String,
    val fullExchangeName: String,
    val exchange: String
)
