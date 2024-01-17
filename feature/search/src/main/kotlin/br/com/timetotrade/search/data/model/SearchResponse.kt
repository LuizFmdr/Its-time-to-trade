package br.com.timetotrade.search.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "count")
    val count: Int,
    @Json(name = "quotes")
    val quoteResponses: List<QuoteResponse>,
)

@JsonClass(generateAdapter = true)
data class QuoteResponse(
    @Json(name = "exchDisp")
    val exchDisp: String,
    @Json(name = "exchange")
    val exchange: String,
    @Json(name = "index")
    val index: String,
    @Json(name = "industry")
    val industry: String?,
    @Json(name = "industryDisp")
    val industryDisp: String?,
    @Json(name = "isYahooFinance")
    val isYahooFinance: Boolean,
    @Json(name = "longname")
    val longname: String?,
    @Json(name = "quoteType")
    val quoteType: String?,
    @Json(name = "score")
    val score: Double,
    @Json(name = "sector")
    val sector: String?,
    @Json(name = "sectorDisp")
    val sectorDisp: String?,
    @Json(name = "shortname")
    val shortname: String?,
    @Json(name = "symbol")
    val symbol: String?,
    @Json(name = "typeDisp")
    val typeDisp: String?
)
