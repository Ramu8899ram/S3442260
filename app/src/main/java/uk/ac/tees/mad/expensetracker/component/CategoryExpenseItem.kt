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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.expensetracker.model.CategoryModel

@Composable
fun CategoryExpenseItem(categoryModel: CategoryModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(end = 16.dp).size(56.dp)
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
                Text(categoryModel.category, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.weight(1f))
                Text("$${categoryModel.expenses}", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }
            Row {
                Text(categoryModel.paymentMode, color = Color.Gray)
                Spacer(Modifier.weight(1f))
                Text(categoryModel.percentage.toString()+"%", color = Color.Gray)
            }
        }
    }
}