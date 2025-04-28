package uk.ac.tees.mad.expensetracker.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.expensetracker.R
import uk.ac.tees.mad.expensetracker.data.local.roomdb.ExpenseEntity
import uk.ac.tees.mad.expensetracker.model.CategoryModel
import uk.ac.tees.mad.expensetracker.model.CurrencyResponse
import uk.ac.tees.mad.expensetracker.util.Constants
import uk.ac.tees.mad.expensetracker.util.Utils

@SuppressLint("DefaultLocale")
@Composable
fun CategoryExpenseItem(
    list: List<ExpenseEntity>,
    categoryModel: CategoryModel,
    totalSum: Double,
    sCurr: Int,
    rate: CurrencyResponse
) {
    val paymentModes = listOf("Cash", "Card", "UPI")
    val pMode = mutableSetOf<Int>()
    list.forEach { item->
        pMode.add(item.paymentMode)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(end = 16.dp)
                .size(56.dp)
                .clip(CircleShape)
                .background(categoryModel.color)
        ) {
            Icon(
                painter = painterResource(categoryModel.icon),
                contentDescription = categoryModel.category,
                tint = Color.Black,
                modifier = Modifier.size(32.dp)
            )
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row {
                val sum = Utils.getExpenseSum(list, rate, Constants.getCurrency(sCurr))
                Text(categoryModel.category, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.weight(1f))
                CurrencyTextRow(
                    text = String.format("%.2f",sum),
                    icon = Constants.getCurrencyIcon(sCurr),
                    fontSize = 22,
                    isBold = true,
                    iconSize = 20
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                val sum = Utils.getExpenseSum(list, rate, Constants.getCurrency(sCurr))
                val percent = if (totalSum == 0.0) 0 else (sum / totalSum) * 100
                pMode.forEachIndexed { idx, item->
                    Text(paymentModes[item-1], color = Color.Gray)
                    if (idx!=pMode.size-1) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.baseline_circle_24),
                            contentDescription = "dot",
                            tint = Color.Gray,
                            modifier = Modifier.padding(2.dp).size(6.dp)
                        )
                    }
                }
                Spacer(Modifier.weight(1f))
                Text("${percent.toInt()}%", color = Color.Gray)
            }
        }
    }
}