package uk.ac.tees.mad.expensetracker.component

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import uk.ac.tees.mad.expensetracker.data.local.roomdb.ExpenseEntity
import uk.ac.tees.mad.expensetracker.model.CurrencyResponse
import uk.ac.tees.mad.expensetracker.util.Utils.prepareChartData

@Composable
fun ExpenseBarChart(expenses: List<ExpenseEntity>, rate: CurrencyResponse) {
    val (chartData, labels) = remember(expenses) { prepareChartData(expenses, rate) }

    AndroidView(
        factory = { context ->
            BarChart(context).apply {
                description.isEnabled = false
                axisRight.isEnabled = false
                setFitBars(true)
                animateY(1000)

                // Customize X-axis
                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    granularity = 1f // Ensures each bar gets a unique label
                    valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return labels.getOrNull(value.toInt()) ?: ""
                        }
                    }
                }
            }
        },
        update = { chart ->
            val dataSet = BarDataSet(chartData, "Expenses Last 30 Days").apply {
                color = Color.BLUE
                valueTextColor = Color.BLACK
            }

            chart.data = BarData(dataSet)
            chart.notifyDataSetChanged()
            chart.invalidate() // Refresh chart
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}

