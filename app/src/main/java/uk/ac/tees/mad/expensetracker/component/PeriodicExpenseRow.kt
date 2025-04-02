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

@Composable
fun PeriodicExpenseRow() {
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
                    Text("Day", fontSize = 16.sp, color = Color.Gray)
                    Text("$52", fontSize = 22.sp, fontWeight = FontWeight.Bold)
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
                    Text("Week", fontSize = 16.sp, color = Color.Gray)
                    Text("$502", fontSize = 22.sp, fontWeight = FontWeight.Bold)
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
                    Text("Month", fontSize = 16.sp, color = Color.Gray)
                    Text("$5020", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}