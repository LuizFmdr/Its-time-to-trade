package br.com.timetotrade.utils

import br.com.timetotrade.BuildConfig

fun onDebug(block: () -> Unit) {
    if (BuildConfig.DEBUG) {
        block()
    }
}
