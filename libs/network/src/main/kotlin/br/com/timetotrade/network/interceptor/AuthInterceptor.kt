package br.com.timetotrade.network.interceptor

import br.com.timetotrade.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-RapidAPI-Key", BuildConfig.API_KEY)
            .addHeader("X-RapidAPI-Host", BuildConfig.API_HOST)
            .build()

        return chain.proceed(request)
    }
}
