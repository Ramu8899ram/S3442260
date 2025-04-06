package uk.ac.tees.mad.expensetracker.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconTextCard(
    onClick:()-> Unit,
    isSelected: Boolean,
    title: String,
     @DrawableRes icon: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card({
            onClick()
        },
            colors = CardDefaults.cardColors(
                containerColor = if (isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surfaceContainerHighest
            )
            ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "icon",
                modifier = Modifier
                    .padding(12.dp)
                    .size(32.dp)
            )
        }
        Text(title)
    }
}