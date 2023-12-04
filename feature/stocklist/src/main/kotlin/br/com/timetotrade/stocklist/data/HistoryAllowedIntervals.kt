package br.com.timetotrade.stocklist.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class HistoryAllowedIntervals {
    @SerialName("5m")
    FIVE_MINUTES,

    @SerialName("15m")
    FIFTEEN_MINUTES,

    @SerialName("30m")
    THIRTY_MINUTES,

    @SerialName("1h")
    ONE_HOUR,

    @SerialName("1d")
    ONE_DAY,

    @SerialName("1wk")
    ONE_WEEK,

    @SerialName("1mo")
    ONE_MONTH,

    @SerialName("3mo")
    THREE_MONTHS
}
