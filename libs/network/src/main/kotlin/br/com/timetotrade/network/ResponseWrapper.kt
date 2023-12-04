package br.com.timetotrade.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseWrapper<B, M>(
    @SerialName("body")
    val body: B,
    @SerialName("meta")
    val meta: M
)


