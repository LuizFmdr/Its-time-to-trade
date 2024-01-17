package br.com.timetotrade

import android.app.Application
import br.com.timetotrade.utils.onDebug
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TimeToTradeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        onDebug {
            Timber.plant(Timber.DebugTree())
        }
    }
}
