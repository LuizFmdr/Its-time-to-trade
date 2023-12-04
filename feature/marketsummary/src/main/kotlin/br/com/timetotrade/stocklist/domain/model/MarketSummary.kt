package br.com.timetotrade.stocklist.domain.model

data class MarketSummary(
    val symbol: String,
    val fullExchangeName: String,
    val exchange: String,
    val shortName: String,
    val regularMarketPreviousClose: RegularMarketValue,
    val spark: Spark,
)

data class Spark(
    val close: List<Float>
)

data class RegularMarketValue(
    val raw: Double,
    val fmt: String,
)
