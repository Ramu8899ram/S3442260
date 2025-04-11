package uk.ac.tees.mad.expensetracker.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.expensetracker.data.local.roomdb.ExpenseEntity
import uk.ac.tees.mad.expensetracker.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){
    private val _expenseList = MutableStateFlow(emptyList<ExpenseEntity>())
    val expenseList: StateFlow<List<ExpenseEntity>> get() = _expenseList

    init {
        viewModelScope.launch {
            repository.getExpenses().collect {
                _expenseList.value = it
            }
        }
    }
}