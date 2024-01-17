package br.com.timetotrade.marketsummary.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarketSummaryResponse(
    @Json(name = "marketSummaryAndSparkResponse")
    val marketSummaryAndSparkResponse: MarketSummaryAndSparkResponse
)

@JsonClass(generateAdapter = true)
data class MarketSummaryAndSparkResponse(
    @Json(name = "result")
    val marketResultList: List<MarketResult>
)

@JsonClass(generateAdapter = true)
data class MarketResult(
    @Json(name = "cryptoTradeable")
    val cryptoTradeable: Boolean,
    @Json(name = "customPriceAlertConfidence")
    val customPriceAlertConfidence: String,
    @Json(name = "exchange")
    val exchange: String,
    @Json(name = "fullExchangeName")
    val fullExchangeName: String,
    @Json(name = "market")
    val market: String,
    @Json(name = "marketState")
    val marketState: String,
    @Json(name = "priceHint")
    val priceHint: Int,
    @Json(name = "quoteType")
    val quoteType: String,
    @Json(name = "region")
    val region: String,
    @Json(name = "regularMarketPreviousClose")
    val regularMarketPreviousClose: RegularMarketPreviousClose,
    @Json(name = "regularMarketTime")
    val regularMarketTime: RegularMarketTime,
    @Json(name = "shortName")
    val shortName: String,
    @Json(name = "sourceInterval")
    val sourceInterval: Int,
    @Json(name = "spark")
    val sparkResponse: SparkResponse,
    @Json(name = "symbol")
    val symbol: String,
    @Json(name = "tradeable")
    val tradeable: Boolean,
    @Json(name = "triggerable")
    val triggerable: Boolean
)

@JsonClass(generateAdapter = true)
data class RegularMarketPreviousClose(
    @Json(name = "fmt")
    val fmt: String,
    @Json(name = "raw")
    val raw: Double
)

@JsonClass(generateAdapter = true)
data class RegularMarketTime(
    @Json(name = "fmt")
    val fmt: String,
    @Json(name = "raw")
    val raw: Int
)

@JsonClass(generateAdapter = true)
data class SparkResponse(
    @Json(name = "chartPreviousClose")
    val chartPreviousClose: Double?,
    @Json(name = "close")
    val close: List<Double>?,
    @Json(name = "dataGranularity")
    val dataGranularity: Int?,
    @Json(name = "end")
    val end: Int?,
    @Json(name = "previousClose")
    val previousClose: Double,
    @Json(name = "start")
    val start: Int?,
    @Json(name = "symbol")
    val symbol: String?,
    @Json(name = "timestamp")
    val timestamp: List<Int>?
)
