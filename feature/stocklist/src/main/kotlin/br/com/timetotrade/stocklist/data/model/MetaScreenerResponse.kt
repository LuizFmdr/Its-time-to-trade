package br.com.timetotrade.stocklist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaScreenerResponse(
    @SerialName("copywrite")
    val copywrite: String,
    @SerialName("count")
    val count: Int,
    @SerialName("jobTimestamp")
    val jobTimestamp: Long,
    @SerialName("processedTime")
    val processedTime: String,
    @SerialName("startInterval")
    val startInterval: Long,
    @SerialName("status")
    val status: Int,
    @SerialName("version")
    val version: String
)
