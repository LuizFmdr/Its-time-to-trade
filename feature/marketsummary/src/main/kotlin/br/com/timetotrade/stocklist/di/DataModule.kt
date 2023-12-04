package br.com.timetotrade.stocklist.di

import br.com.timetotrade.stocklist.data.MarketSummaryDataSource
import br.com.timetotrade.stocklist.data.MarketSummaryDataSourceImpl
import br.com.timetotrade.stocklist.domain.MarketSummaryRepository
import br.com.timetotrade.stocklist.domain.MarketSummaryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataSource(
        moviesDataSourceImpl: MarketSummaryDataSourceImpl
    ): MarketSummaryDataSource

    @Binds
    abstract fun bindStocksRepository(
        stocksRepositoryImpl: MarketSummaryRepositoryImpl
    ): MarketSummaryRepository
}
