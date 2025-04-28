package uk.ac.tees.mad.expensetracker.screen.history

import android.graphics.Bitmap
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import uk.ac.tees.mad.expensetracker.component.ActivityItem
import uk.ac.tees.mad.expensetracker.component.ReceiptDialog
import uk.ac.tees.mad.expensetracker.util.Utils

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val expenseList by viewModel.expenseList.collectAsState()
    val showReceipt = remember { mutableStateOf(false) }
    val selectedReceipt = remember { mutableStateOf<Bitmap?>(null) }
    LazyColumn(modifier = modifier) {
        items(expenseList) { expense->
            ActivityItem(expense){
                selectedReceipt.value = Utils.base64ToBitmap(expense.receiptImage)
                showReceipt.value = true
            }
        }
    }

    if (showReceipt.value){
        ReceiptDialog(selectedReceipt.value) {
            showReceipt.value = false
        }
    }
}