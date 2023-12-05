package br.com.timetotrade.utils

import com.squareup.leakcanary.core.BuildConfig

fun onDebug(block: () -> Unit) {
    if (BuildConfig.DEBUG) {
        block()
    }
}
