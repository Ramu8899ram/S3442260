package uk.ac.tees.mad.expensetracker.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.expensetracker.R

@Composable
fun PaymentModeSelector(
    onClick:(Int)-> Unit,
    selectedMode: Int
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Payment Mode:", fontSize = 22.sp)
        Spacer(Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconTextCard({ onClick(1) }, selectedMode == 1, "Cash", R.drawable.cash)
            IconTextCard({ onClick(2) }, selectedMode == 2, "Card", R.drawable.card)
            IconTextCard({ onClick(3) }, selectedMode == 3, "UPI", R.drawable.upi)
        }
    }
}