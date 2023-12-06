package br.com.timetotrade.stocklist.domain

import br.com.timetotrade.stocklist.domain.model.MarketSummary
import br.com.timetotrade.stocklist.domain.model.SearchResult
import kotlinx.coroutines.flow.Flow

interface MarketSummaryRepository {

    fun getMarketSummary(selectedMarket: String): Flow<List<MarketSummary>>

    fun search(query: String, selectedMarket: String): Flow<List<SearchResult>>
}
