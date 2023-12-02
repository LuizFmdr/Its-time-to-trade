package br.com.timetotrade

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    fun someShit() {
        val moreMagicNumber = 100
        ((9 + 25)..moreMagicNumber).forEach {
            println(it)
        }
    }
}
