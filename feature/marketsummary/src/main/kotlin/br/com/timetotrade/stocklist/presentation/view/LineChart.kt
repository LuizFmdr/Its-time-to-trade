package br.com.timetotrade.stocklist.presentation.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import br.com.timetotrade.desingsystem.LightCarmin
import br.com.timetotrade.desingsystem.LightOlive
import br.com.timetotrade.stocklist.domain.model.Spark

@Composable
fun LineChart(modifier: Modifier = Modifier, spark: Spark) {

    Row(modifier = modifier) {

        val lineColor =
            if (spark.closeList.last() > spark.closeList.first()) LightOlive else LightCarmin

        for (pair in spark.closeZipList) {

            val fromValuePercentage = getValuePercentageForRange(pair.first, spark.max, spark.min)
            val toValuePercentage = getValuePercentageForRange(pair.second, spark.max, spark.min)

            Canvas(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                onDraw = {
                    val fromPoint = Offset(
                        x = 0f,
                        y = size.height.times(1 - fromValuePercentage)
                    )
                    val toPoint =
                        Offset(
                            x = size.width,
                            y = size.height.times(1 - toValuePercentage)
                        )

                    drawLine(
                        color = lineColor,
                        start = fromPoint,
                        end = toPoint,
                        strokeWidth = 3f
                    )
                })
        }
    }
}

private fun getValuePercentageForRange(value: Float, max: Float, min: Float) =
    (value - min) / (max - min)
