package br.com.timetotrade.marketsummary.db.di

import android.content.Context
import androidx.room.Room
import br.com.timetotrade.marketsummary.db.TimeToTradeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesTimeToTradeDataBase(
        @ApplicationContext context: Context,
    ): TimeToTradeDatabase = Room.databaseBuilder(
        context,
        TimeToTradeDatabase::class.java,
        "timetotrade-database",
    ).build()
}
