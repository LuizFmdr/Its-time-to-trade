package br.com.timetotrade.stocklist.domain

import br.com.timetotrade.stocklist.domain.model.MarketSummary
import kotlinx.coroutines.flow.Flow

interface MarketSummaryRepository {

    fun getMarketSummary(): Flow<List<MarketSummary>>
}
