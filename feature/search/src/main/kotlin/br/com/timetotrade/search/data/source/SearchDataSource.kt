package br.com.timetotrade.search.data.source

import br.com.timetotrade.search.data.SearchService
import br.com.timetotrade.search.data.model.QuoteResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface SearchDataSource {
    fun search(query: String): Flow<List<QuoteResponse>>
}

class SearchDataSourceImpl @Inject constructor(
    private val searchService: SearchService
) : SearchDataSource {

    override fun search(query: String): Flow<List<QuoteResponse>> {
        return flow {
            emit(searchService.search(query).quoteResponses)
        }
    }
}
