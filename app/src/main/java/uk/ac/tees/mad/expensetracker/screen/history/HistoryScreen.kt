package uk.ac.tees.mad.expensetracker.screen.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import uk.ac.tees.mad.expensetracker.component.ActivityItem

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val expenseList by viewModel.expenseList.collectAsState()
    LazyColumn(modifier = modifier) {
        items(expenseList) { expense->
            ActivityItem(expense)
        }
    }
}