package uk.ac.tees.mad.expensetracker.component

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.expensetracker.data.local.roomdb.ExpenseEntity
import uk.ac.tees.mad.expensetracker.model.CategoryModel
import uk.ac.tees.mad.expensetracker.model.CurrencyResponse
import uk.ac.tees.mad.expensetracker.util.Constants
import uk.ac.tees.mad.expensetracker.util.Utils

@Composable
fun CategoryExpenseItem(
    list: List<ExpenseEntity>,
    categoryModel: CategoryModel,
    totalSum: Double,
    sCurr: Int,
    rate: CurrencyResponse
) {
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
                val sum = Utils.getExpenseSum(list, rate)
                Text(categoryModel.category, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.weight(1f))
                CurrencyTextRow(
                    text = "$sum",
                    icon = Constants.getCurrencyIcon(sCurr),
                    fontSize = 22,
                    isBold = true,
                    iconSize = 20
                )
            }
            Row {
                val sum = Utils.getExpenseSum(list, rate)
                val percent = if (totalSum == 0.0) 0 else (sum / totalSum) * 100
                Text("Cash", color = Color.Gray)
                Spacer(Modifier.weight(1f))
                Text("${percent.toInt()}%", color = Color.Gray)
            }
        }
    }
}