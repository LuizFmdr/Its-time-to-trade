package br.com.timetotrade.search.domain.model

data class SearchResult(
    val shortName: String,
    val longName: String,
    val symbol: String,
    val exchDisp: String,
    val industry: String,
    val exchange: String,

)

