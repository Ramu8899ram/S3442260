package uk.ac.tees.mad.expensetracker.screen.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.expensetracker.component.CategoryExpenseItem
import uk.ac.tees.mad.expensetracker.component.ExpenseBarChart
import uk.ac.tees.mad.expensetracker.component.PeriodicExpenseRow
import uk.ac.tees.mad.expensetracker.util.Constants
import uk.ac.tees.mad.expensetracker.util.Utils

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel) {
    val last30Days by viewModel.last30Days.collectAsState()
    val selectedCurrency by viewModel.selectedCurrency.collectAsState()
    val exchangeRate by viewModel.exchangeRate.collectAsState()
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if (exchangeRate!=null){
            ExpenseBarChart(last30Days, exchangeRate!!, selectedCurrency)
            Spacer(Modifier.height(8.dp))
            PeriodicExpenseRow(last30Days,selectedCurrency, exchangeRate!!)
            Spacer(Modifier.height(20.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                items(6) { idx ->
                    CategoryExpenseItem(
                        last30Days.filter { it.category == idx+1 },
                        Constants.getCategoryInfo(idx+1),
                        Utils.getExpenseSum(last30Days, exchangeRate!!, Constants.getCurrency(selectedCurrency)),
                        selectedCurrency,
                        exchangeRate!!
                    )
                }
            }
        }

    }
}