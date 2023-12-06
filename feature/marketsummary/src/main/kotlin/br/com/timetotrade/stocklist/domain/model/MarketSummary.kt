package br.com.timetotrade.stocklist.domain.model

data class MarketSummary(
    val formattedName: String,
    val symbol: String,
    val fullExchangeName: String,
    val exchange: String,
    val shortName: String,
    val regularMarketPreviousClose: RegularMarketValue,
    val spark: Spark,
    val currentPoints: Double,
    val todayComparedToPreviousClose: Double,
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
