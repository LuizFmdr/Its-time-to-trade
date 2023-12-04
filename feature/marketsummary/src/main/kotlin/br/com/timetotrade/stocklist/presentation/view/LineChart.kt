package br.com.timetotrade.stocklist.presentation.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import br.com.timetotrade.desingsystem.LightCarmin
import br.com.timetotrade.desingsystem.LightOlive

@Composable
fun LineChart(modifier: Modifier = Modifier, list: List<Float>) {
    val zipList: List<Pair<Float, Float>> = list.zipWithNext()

    Row(modifier = modifier) {
        val max = list.max()
        val min = list.min()

        val lineColor =
            if (list.last() > list.first()) LightOlive else LightCarmin

        for (pair in zipList) {

            val fromValuePercentage = getValuePercentageForRange(pair.first, max, min)
            val toValuePercentage = getValuePercentageForRange(pair.second, max, min)

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
