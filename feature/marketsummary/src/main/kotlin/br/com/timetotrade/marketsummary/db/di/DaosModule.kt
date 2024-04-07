package br.com.timetotrade.marketsummary.db.di

import br.com.timetotrade.marketsummary.db.TimeToTradeDatabase
import br.com.timetotrade.marketsummary.db.dao.SupportedCurrenciesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesTopicsDao(
        database: TimeToTradeDatabase,
    ): SupportedCurrenciesDao = database.supportedCurrenciesDao()
}
