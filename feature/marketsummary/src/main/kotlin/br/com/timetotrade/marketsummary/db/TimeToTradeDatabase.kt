package br.com.timetotrade.marketsummary.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.timetotrade.marketsummary.db.dao.SupportedCurrenciesDao
import br.com.timetotrade.marketsummary.db.model.SupportedCurrenciesEntity

@Database(
    entities = [
        SupportedCurrenciesEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
internal abstract class TimeToTradeDatabase : RoomDatabase() {
    abstract fun supportedCurrenciesDao(): SupportedCurrenciesDao
}
