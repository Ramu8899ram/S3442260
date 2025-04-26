package uk.ac.tees.mad.expensetracker.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DashboardTopAppbar(icon: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 32.dp, bottom = 8.dp)
            .fillMaxWidth()
    ) {
        CurrencyTextRow(
            text = "10245.00",
            icon = icon,
            fontSize = 24,
            isBold = true,
            iconSize = 22
        )
        Text("Total Balance", color = Color.Gray, fontSize = 12.sp)
    }
}