package br.com.timetotrade.marketsummary.di

import br.com.timetotrade.marketsummary.data.MarketService
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
    ): MarketService {
        return retrofit.create(MarketService::class.java)
    }
}
