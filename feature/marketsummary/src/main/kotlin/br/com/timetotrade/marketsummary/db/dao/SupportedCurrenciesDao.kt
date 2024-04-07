package br.com.timetotrade.marketsummary.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import br.com.timetotrade.marketsummary.db.model.SupportedCurrenciesEntity

@Dao
interface SupportedCurrenciesDao {
    @Query(value = "SELECT * FROM supported_currencies")
    suspend fun getOneOffSupportedCurrenciesEntities(): List<SupportedCurrenciesEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreSupportedCurrencies(topicEntities: List<SupportedCurrenciesEntity>): List<Long>

    @Upsert
    suspend fun upsertSupportedCurrencies(entities: List<SupportedCurrenciesEntity>)
}
