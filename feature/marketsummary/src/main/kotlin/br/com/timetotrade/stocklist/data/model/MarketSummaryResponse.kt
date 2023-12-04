package br.com.timetotrade.stocklist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarketSummaryResponse(
    @SerialName("marketSummaryAndSparkResponse")
    val marketSummaryAndSparkResponse: MarketSummaryAndSparkResponse
)

@Serializable
data class MarketSummaryAndSparkResponse(
    @SerialName("result")
    val marketResultList: List<MarketResult>
)

@Serializable
data class MarketResult(
    @SerialName("cryptoTradeable")
    val cryptoTradeable: Boolean,
    @SerialName("customPriceAlertConfidence")
    val customPriceAlertConfidence: String,
    @SerialName("exchange")
    val exchange: String,
    @SerialName("exchangeDataDelayedBy")
    val exchangeDataDelayedBy: Int,
    @SerialName("exchangeTimezoneName")
    val exchangeTimezoneName: String,
    @SerialName("exchangeTimezoneShortName")
    val exchangeTimezoneShortName: String,
    @SerialName("firstTradeDateMilliseconds")
    val firstTradeDateMilliseconds: Long,
    @SerialName("fullExchangeName")
    val fullExchangeName: String,
    @SerialName("gmtOffSetMilliseconds")
    val gmtOffSetMilliseconds: Int,
    @SerialName("language")
    val language: String,
    @SerialName("market")
    val market: String,
    @SerialName("marketState")
    val marketState: String,
    @SerialName("priceHint")
    val priceHint: Int,
    @SerialName("quoteType")
    val quoteType: String,
    @SerialName("region")
    val region: String,
    @SerialName("regularMarketPreviousClose")
    val regularMarketPreviousClose: RegularMarketPreviousClose,
    @SerialName("regularMarketTime")
    val regularMarketTime: RegularMarketTime,
    @SerialName("shortName")
    val shortName: String,
    @SerialName("sourceInterval")
    val sourceInterval: Int,
    @SerialName("spark")
    val sparkResponse: SparkResponse,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("tradeable")
    val tradeable: Boolean,
    @SerialName("triggerable")
    val triggerable: Boolean
)

@Serializable
data class RegularMarketPreviousClose(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class RegularMarketTime(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Int
)

@Serializable
data class SparkResponse(
    @SerialName("chartPreviousClose")
    val chartPreviousClose: Double,
    @SerialName("close")
    val close: List<Double>,
    @SerialName("dataGranularity")
    val dataGranularity: Int,
    @SerialName("end")
    val end: Int,
    @SerialName("previousClose")
    val previousClose: Double,
    @SerialName("start")
    val start: Int,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("timestamp")
    val timestamp: List<Int>
)
