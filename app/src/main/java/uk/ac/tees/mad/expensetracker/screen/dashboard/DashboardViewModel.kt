package uk.ac.tees.mad.expensetracker.screen.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.expensetracker.data.local.datastore.DataStoreManager
import uk.ac.tees.mad.expensetracker.data.local.roomdb.ExpenseEntity
import uk.ac.tees.mad.expensetracker.data.repository.Repository
import uk.ac.tees.mad.expensetracker.model.CurrencyResponse
import uk.ac.tees.mad.expensetracker.util.Utils
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: Repository,
    private val dataStoreManager: DataStoreManager
): ViewModel() {
    private val _last30Days = MutableStateFlow(emptyList<ExpenseEntity>())
    val last30Days: StateFlow<List<ExpenseEntity>> get() = _last30Days
    private val startDate = System.currentTimeMillis() - (30L * 24 * 60 * 60 * 1000)
    private val _selectedCurrency = MutableStateFlow(0)
    val selectedCurrency:StateFlow<Int> get() = _selectedCurrency
    private val _exchangeRate = MutableStateFlow<CurrencyResponse?>(null)
    val exchangeRate:StateFlow<CurrencyResponse?> get() = _exchangeRate

    init {
        viewModelScope.launch {
            repository.getExpenses().collect {
                _last30Days.value = it.filter { expense->
                    expense.time >= startDate
                }
            }
        }
        viewModelScope.launch {
            dataStoreManager.selectedCurrencyFlow.collect{_selectedCurrency.value = it}
        }

        viewModelScope.launch {
            dataStoreManager.exchangeRateFow.collect {
                _exchangeRate.value = Utils.jsonToObject(it)
            }
        }
    }
}