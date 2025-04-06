package uk.ac.tees.mad.expensetracker.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.expensetracker.util.Constants

@Composable
fun CategorySelector(
    onClick:(Int)-> Unit,
    selectedCategory: Int
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Category:", fontSize = 22.sp)
        Spacer(Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(Constants.getCategoryList()) { idx,item->
                IconTextCard(
                    { onClick(idx + 1) },
                    selectedCategory == idx + 1,
                    item.category,
                    item.icon
                )
            }
        }
    }
}