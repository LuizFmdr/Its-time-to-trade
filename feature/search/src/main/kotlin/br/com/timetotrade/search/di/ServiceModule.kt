package br.com.timetotrade.search.di

import br.com.timetotrade.search.data.SearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {

    @Provides
    @Singleton
    fun provideStockService(
        retrofit: Retrofit
    ): SearchService {
        return retrofit.create(SearchService::class.java)
    }
}
