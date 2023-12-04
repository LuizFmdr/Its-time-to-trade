package br.com.timetotrade.stocklist.di

import br.com.timetotrade.stocklist.data.TradeDataSource
import br.com.timetotrade.stocklist.data.TradeDataSourceImpl
import br.com.timetotrade.stocklist.domain.TradeStocksRepository
import br.com.timetotrade.stocklist.domain.TradeStocksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataSource(
        moviesDataSourceImpl: TradeDataSourceImpl
    ): TradeDataSource

    @Binds
    abstract fun bindStocksRepository(
        stocksRepositoryImpl: TradeStocksRepositoryImpl
    ): TradeStocksRepository
}
