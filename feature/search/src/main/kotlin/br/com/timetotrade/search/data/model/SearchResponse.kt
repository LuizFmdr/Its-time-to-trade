package br.com.timetotrade.search.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("count")
    val count: Int,
    @SerialName("quotes")
    val quoteResponses: List<QuoteResponse>,
)

@Serializable
data class QuoteResponse(
    @SerialName("dispSecIndFlag")
    val dispSecIndFlag: Boolean?,
    @SerialName("exchDisp")
    val exchDisp: String,
    @SerialName("exchange")
    val exchange: String,
    @SerialName("index")
    val index: String,
    @SerialName("industry")
    val industry: String,
    @SerialName("industryDisp")
    val industryDisp: String,
    @SerialName("isYahooFinance")
    val isYahooFinance: Boolean,
    @SerialName("longname")
    val longname: String,
    @SerialName("nameChangeDate")
    val nameChangeDate: String?,
    @SerialName("newListingDate")
    val newListingDate: String?,
    @SerialName("prevName")
    val prevName: String?,
    @SerialName("quoteType")
    val quoteType: String,
    @SerialName("score")
    val score: Int,
    @SerialName("sector")
    val sector: String,
    @SerialName("sectorDisp")
    val sectorDisp: String,
    @SerialName("shortname")
    val shortname: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("typeDisp")
    val typeDisp: String
)
