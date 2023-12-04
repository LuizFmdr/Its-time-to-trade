package br.com.timetotrade.stocklist.di

import br.com.timetotrade.stocklist.data.repository.MarketSummaryRepositoryImpl
import br.com.timetotrade.stocklist.data.source.MarketSummaryDataSource
import br.com.timetotrade.stocklist.data.source.MarketSummaryDataSourceImpl
import br.com.timetotrade.stocklist.domain.MarketSummaryRepository
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
