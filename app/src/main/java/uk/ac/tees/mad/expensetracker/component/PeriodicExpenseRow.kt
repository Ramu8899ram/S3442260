package uk.ac.tees.mad.expensetracker.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.expensetracker.data.local.roomdb.ExpenseEntity
import uk.ac.tees.mad.expensetracker.model.CurrencyResponse
import uk.ac.tees.mad.expensetracker.util.Constants
import uk.ac.tees.mad.expensetracker.util.Utils

@Composable
fun PeriodicExpenseRow(list: List<ExpenseEntity>, selectedCurrency:Int, rate: CurrencyResponse) {
    val today = System.currentTimeMillis() - (24 * 60 * 60 * 1000)
    val weekly = System.currentTimeMillis() - (7L * 24 * 60 * 60 * 1000)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
    ) {
        Card {
            Box(
                contentAlignment = Alignment.Center,
                modifier =  Modifier
                    .width(100.dp)
                    .height(70.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    val dayAmount = Utils.getExpenseSum(list.filter { it.time >= today }, rate)
                    Text("Day", fontSize = 16.sp, color = Color.Gray)
                    CurrencyTextRow(
                        text = "$dayAmount",
                        icon = Constants.getCurrencyIcon(selectedCurrency),
                        fontSize = 22,
                        isBold = true,
                        iconSize = 20
                    )
                }
            }
        }
        Card {
            Box(
                contentAlignment = Alignment.Center,
                modifier =  Modifier
                    .width(100.dp)
                    .height(70.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    val weeklyAmount = Utils.getExpenseSum(list.filter { it.time >= weekly }, rate)
                    Text("Week", fontSize = 16.sp, color = Color.Gray)
                    CurrencyTextRow(
                        text = "$weeklyAmount",
                        icon = Constants.getCurrencyIcon(selectedCurrency),
                        fontSize = 22,
                        isBold = true,
                        iconSize = 20
                    )
                }
            }
        }
        Card {
            Box(
                contentAlignment = Alignment.Center,
                modifier =  Modifier
                    .width(100.dp)
                    .height(70.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    val monthlyAmount = Utils.getExpenseSum(list, rate)
                    Text("Month", fontSize = 16.sp, color = Color.Gray)
                    CurrencyTextRow(
                        text = "$monthlyAmount",
                        icon = Constants.getCurrencyIcon(selectedCurrency),
                        fontSize = 22,
                        isBold = true,
                        iconSize = 20
                    )
                }
            }
        }
    }
}