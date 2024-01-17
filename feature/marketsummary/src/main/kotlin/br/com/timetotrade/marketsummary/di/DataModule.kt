package br.com.timetotrade.marketsummary.di

import br.com.timetotrade.marketsummary.data.repository.MarketSummaryRepositoryImpl
import br.com.timetotrade.marketsummary.data.source.MarketSummaryDataSource
import br.com.timetotrade.marketsummary.data.source.MarketSummaryDataSourceImpl
import br.com.timetotrade.marketsummary.domain.MarketSummaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataSource(
        summaryDataSourceImpl: MarketSummaryDataSourceImpl
    ): MarketSummaryDataSource

    @Binds
    abstract fun bindSummaryRepository(
        summaryRepositoryImpl: MarketSummaryRepositoryImpl
    ): MarketSummaryRepository
}
