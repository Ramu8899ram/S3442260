package uk.ac.tees.mad.expensetracker.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.expensetracker.data.local.roomdb.ExpenseEntity
import uk.ac.tees.mad.expensetracker.util.Constants
import uk.ac.tees.mad.expensetracker.util.Utils.getDateTimeFromMillis

@Composable
fun ActivityItem(
    expense: ExpenseEntity,
    onClick:()-> Unit
) {
    val catModel = Constants.getCategoryInfo(expense.category)
    Column(
        modifier = Modifier.padding(16.dp)
            .clickable{onClick()}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(end = 16.dp).size(42.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(catModel.color)
            ) {
                Icon(
                    painter = painterResource(catModel.icon),
                    contentDescription = "category",
                    tint = Color.Black,
                    modifier = Modifier.size(26.dp)
                )
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(catModel.category, fontSize = 20.sp)
                Text(getDateTimeFromMillis(expense.time), fontSize = 16.sp, color = Color.Gray)
            }
            CurrencyTextRow(
                expense.amount.toString(),
                Constants.getCurrencyIcon(expense.currency),
                22,
                false,
                20
            )
        }
        Spacer(Modifier.height(4.dp))
        HorizontalDivider(thickness = 1.dp, color = catModel.color)
    }
}