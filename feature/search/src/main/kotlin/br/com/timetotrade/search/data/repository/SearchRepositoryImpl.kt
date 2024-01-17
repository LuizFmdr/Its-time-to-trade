package br.com.timetotrade.search.data.repository

import br.com.timetotrade.search.data.source.SearchDataSource
import br.com.timetotrade.search.domain.SearchRepository
import br.com.timetotrade.search.domain.model.SearchResult
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchRepositoryImpl @Inject constructor(
    private val dataSource: SearchDataSource,
) : SearchRepository {

    override fun search(query: String): Flow<List<SearchResult>> {
        return dataSource.search(query)
            .map {
                it.map { response ->
                    SearchResult(
                        shortName = response.shortname.orEmpty(),
                        longName = response.longname ?: response.shortname.orEmpty(),
                        symbol = response.symbol.orEmpty(),
                        exchDisp = response.exchDisp,
                        industry = response.industry.orEmpty(),
                        exchange = response.exchange,
                        score = response.score
                    )
                }
            }
    }
}
