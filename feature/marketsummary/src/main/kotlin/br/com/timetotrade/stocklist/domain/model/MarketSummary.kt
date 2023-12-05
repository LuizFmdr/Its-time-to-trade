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
    val closeZipList: List<Pair<Float, Float>>,
    val closeList: List<Float>,
    val max: Float,
    val min: Float,
)

data class RegularMarketValue(
    val raw: Double,
    val fmt: String,
)
