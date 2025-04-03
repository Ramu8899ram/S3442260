package uk.ac.tees.mad.expensetracker.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CurrencySelector(
    currentCurrency: String,
    onCurrencyChange: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val currencyOptions = listOf("usd","inr","eur", "jpy", "gbp", "aud","cad", "chf")

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .clickable { expanded = true },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Selected Currency: ",
                modifier = Modifier.weight(1f))
            Text(text = currentCurrency.uppercase(), fontSize = 18.sp)
            Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Icon")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            currencyOptions.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.uppercase(), fontSize = 16.sp) },
                    onClick = {
                        onCurrencyChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

