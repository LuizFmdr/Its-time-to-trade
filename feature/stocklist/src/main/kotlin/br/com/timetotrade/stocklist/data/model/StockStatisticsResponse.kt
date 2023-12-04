package br.com.timetotrade.stocklist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StockStatisticsMetaResponse(
    @SerialName("copywrite")
    val copywrite: String,
    @SerialName("modules")
    val modules: String,
    @SerialName("processedTime")
    val processedTime: String,
    @SerialName("status")
    val status: Int,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("version")
    val version: String
)

@Serializable
data class StockStatisticsBodyResponse(
    @SerialName("beta")
    val beta: Beta,
    @SerialName("bookValue")
    val bookValue: BookValue,
    @SerialName("category")
    val category: String?,
    @SerialName("dateShortInterest")
    val dateShortInterest: DateShortInterest,
    @SerialName("earningsQuarterlyGrowth")
    val earningsQuarterlyGrowth: EarningsQuarterlyGrowth,
    @SerialName("enterpriseToEbitda")
    val enterpriseToEbitda: EnterpriseToEbitda,
    @SerialName("enterpriseToRevenue")
    val enterpriseToRevenue: EnterpriseToRevenue,
    @SerialName("enterpriseValue")
    val enterpriseValue: EnterpriseValue,
    @SerialName("fiveYearAverageReturn")
    val fiveYearAverageReturn: List<Double>,
    @SerialName("floatShares")
    val floatShares: FloatShares,
    @SerialName("forwardEps")
    val forwardEps: ForwardEps,
    @SerialName("forwardPE")
    val forwardPE: ForwardPE,
    @SerialName("fundFamily")
    val fundFamily: String?,
    @SerialName("heldPercentInsiders")
    val heldPercentInsiders: HeldPercentInsiders,
    @SerialName("heldPercentInstitutions")
    val heldPercentInstitutions: HeldPercentInstitutions,
    @SerialName("impliedSharesOutstanding")
    val impliedSharesOutstanding: ImpliedSharesOutstanding,
    @SerialName("lastDividendDate")
    val lastDividendDate: LastDividendDate,
    @SerialName("lastDividendValue")
    val lastDividendValue: LastDividendValue,
    @SerialName("lastFiscalYearEnd")
    val lastFiscalYearEnd: LastFiscalYearEnd,
    @SerialName("lastSplitDate")
    val lastSplitDate: LastSplitDate,
    @SerialName("lastSplitFactor")
    val lastSplitFactor: String,
    @SerialName("maxAge")
    val maxAge: Int,
    @SerialName("mostRecentQuarter")
    val mostRecentQuarter: MostRecentQuarter,
    @SerialName("netIncomeToCommon")
    val netIncomeToCommon: NetIncomeToCommon,
    @SerialName("nextFiscalYearEnd")
    val nextFiscalYearEnd: NextFiscalYearEnd,
    @SerialName("pegRatio")
    val pegRatio: PegRatio,
    @SerialName("priceHint")
    val priceHint: PriceHint,
    @SerialName("priceToBook")
    val priceToBook: PriceToBook,

    @SerialName("profitMargins")
    val profitMargins: ProfitMargins,

    @SerialName("SandP52WeekChange")
    val sandP52WeekChange: SandP52WeekChange,
    @SerialName("sharesOutstanding")
    val sharesOutstanding: SharesOutstanding,
    @SerialName("sharesPercentSharesOut")
    val sharesPercentSharesOut: SharesPercentSharesOut,
    @SerialName("sharesShort")
    val sharesShort: SharesShort,
    @SerialName("sharesShortPreviousMonthDate")
    val sharesShortPreviousMonthDate: SharesShortPreviousMonthDate,
    @SerialName("sharesShortPriorMonth")
    val sharesShortPriorMonth: SharesShortPriorMonth,
    @SerialName("shortPercentOfFloat")
    val shortPercentOfFloat: ShortPercentOfFloat,
    @SerialName("shortRatio")
    val shortRatio: ShortRatio,
    @SerialName("trailingEps")
    val trailingEps: TrailingEps,
    @SerialName("52WeekChange")
    val weekChange: WeekChange,
)

@Serializable
data class Beta(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class BookValue(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class DateShortInterest(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Int
)

@Serializable
data class EarningsQuarterlyGrowth(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class EnterpriseToEbitda(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class EnterpriseToRevenue(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class EnterpriseValue(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("longFmt")
    val longFmt: String,
    @SerialName("raw")
    val raw: Long
)

@Serializable
data class FloatShares(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("longFmt")
    val longFmt: String,
    @SerialName("raw")
    val raw: Long
)

@Serializable
data class ForwardEps(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class ForwardPE(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class HeldPercentInsiders(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class HeldPercentInstitutions(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class ImpliedSharesOutstanding(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("longFmt")
    val longFmt: String,
    @SerialName("raw")
    val raw: Long
)

@Serializable
data class LastDividendDate(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Int
)

@Serializable
data class LastDividendValue(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class LastFiscalYearEnd(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Int
)

@Serializable
data class LastSplitDate(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Int
)

@Serializable
data class MostRecentQuarter(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Int
)

@Serializable
data class NetIncomeToCommon(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("longFmt")
    val longFmt: String,
    @SerialName("raw")
    val raw: Long
)

@Serializable
data class NextFiscalYearEnd(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Int
)

@Serializable
data class PegRatio(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class PriceHint(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("longFmt")
    val longFmt: String,
    @SerialName("raw")
    val raw: Int
)

@Serializable
data class PriceToBook(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class ProfitMargins(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class SandP52WeekChange(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class SharesOutstanding(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("longFmt")
    val longFmt: String,
    @SerialName("raw")
    val raw: Long
)

@Serializable
data class SharesPercentSharesOut(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class SharesShort(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("longFmt")
    val longFmt: String,
    @SerialName("raw")
    val raw: Int
)

@Serializable
data class SharesShortPreviousMonthDate(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Int
)

@Serializable
data class SharesShortPriorMonth(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("longFmt")
    val longFmt: String,
    @SerialName("raw")
    val raw: Int
)

@Serializable
data class ShortPercentOfFloat(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class ShortRatio(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class TrailingEps(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

@Serializable
data class WeekChange(
    @SerialName("fmt")
    val fmt: String,
    @SerialName("raw")
    val raw: Double
)

