package br.com.timetotrade.search.data

import br.com.timetotrade.search.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("auto-complete")
    suspend fun search(
        @Query("q") query: String,
    ): SearchResponse
}
