package br.com.timetotrade.search.domain

import br.com.timetotrade.search.domain.model.SearchResult
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun search(query: String): Flow<List<SearchResult>>
}
