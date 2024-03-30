package br.com.timetotrade.marketsummary.domain

import br.com.timetotrade.marketsummary.domain.model.MarketSummary
import kotlinx.coroutines.flow.Flow

interface MarketSummaryRepository {

    fun getMarketSummary(selectedMarket: String): Flow<List<MarketSummary>>
}
