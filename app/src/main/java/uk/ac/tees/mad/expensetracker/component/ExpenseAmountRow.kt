package uk.ac.tees.mad.expensetracker.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.expensetracker.util.Constants

@Composable
fun ExpenseAmountRow(
    onAmountChange:(String)-> Unit,
    onCurrencyChange:(Int)-> Unit
) {
    val amount = rememberSaveable { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var currentCurrency by rememberSaveable { mutableIntStateOf(0) }
    val currencyOptions = listOf("usd","inr","eur", "jpy", "gbp", "aud","cad", "chf")
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Amount:", fontSize = 22.sp)
        Spacer(Modifier.height(8.dp))
        Row {

            TextField(
                value = amount.value,
                onValueChange = { amount.value = it
                                onAmountChange(it)},
                placeholder = { Text("0.00") },
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(Constants.getCurrencyIcon(currentCurrency)),
                        contentDescription = "currency_symbol",
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.weight(1f)
            )

            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .clickable { expanded = true },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = currencyOptions[currentCurrency].uppercase(), fontSize = 18.sp)
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Icon")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                currencyOptions.forEachIndexed { idx,option ->
                    DropdownMenuItem(
                        text = { Text(option.uppercase(), fontSize = 16.sp) },
                        onClick = {
                            currentCurrency = idx
                            onCurrencyChange(idx)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}