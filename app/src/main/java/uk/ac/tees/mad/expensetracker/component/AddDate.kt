package uk.ac.tees.mad.expensetracker.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.expensetracker.util.Utils.getDate

@Composable
fun AddDate(
    date: Long,
    onClick:()-> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Date:", fontSize = 22.sp)
        Spacer(Modifier.height(8.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.LightGray.copy(0.4f)
                )
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .clickable {
                    onClick()
                }
        ) {
            Text(
                getDate(date),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}