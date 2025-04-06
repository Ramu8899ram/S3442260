package uk.ac.tees.mad.expensetracker.screen.addexpense

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.expensetracker.component.AddNotes
import uk.ac.tees.mad.expensetracker.component.ExpenseAmountRow
import uk.ac.tees.mad.expensetracker.component.PaymentModeSelector
import uk.ac.tees.mad.expensetracker.component.CategorySelector
import uk.ac.tees.mad.expensetracker.component.AddReceipt

@Composable
fun AddExpenseScreen() {
    val selectedPaymentMode = rememberSaveable { mutableIntStateOf(0) }
    val selectedCategory = rememberSaveable { mutableIntStateOf(0) }
    val note = rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(top = 30.dp).fillMaxWidth()
            ){
                Text(
                    "Add Expense",
                    fontSize = 26.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { paddingValues->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
        ){
            ExpenseAmountRow()
            PaymentModeSelector(
                {selectedPaymentMode.intValue = it},
                selectedPaymentMode.intValue
            )
            CategorySelector(
                {selectedCategory.intValue = it},
                selectedCategory.intValue
            )
            AddNotes(note.value) {
                note.value = it
            }
            AddReceipt()
            Spacer(modifier = Modifier.weight(1f))
            Button({}, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("Add", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}