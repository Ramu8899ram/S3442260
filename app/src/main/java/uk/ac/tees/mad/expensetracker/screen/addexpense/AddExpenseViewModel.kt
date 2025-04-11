package uk.ac.tees.mad.expensetracker.screen.addexpense

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uk.ac.tees.mad.expensetracker.data.local.roomdb.ExpenseEntity
import uk.ac.tees.mad.expensetracker.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    fun addExpense(
        amount: String, currency: Int, pMode: Int,
        category: Int, note: String, context: Context
    ) {
        if (amount.isEmpty()){
            Toast.makeText(context, "Add amount", Toast.LENGTH_SHORT).show()
            return
        }
        else if (pMode==0){
            Toast.makeText(context, "Select payment mode", Toast.LENGTH_SHORT).show()
            return
        }
        else if (category==0){
            Toast.makeText(context, "Select category", Toast.LENGTH_SHORT).show()
            return
        }
        val entity = ExpenseEntity(
            amount = amount.toDouble(),
            currency = currency,
            paymentMode = pMode,
            category = category,
            note = note,
            receiptImage = ""
        )
        viewModelScope.launch {
            repository.insertExpense(entity)
            Toast.makeText(context, "Expense add successfully", Toast.LENGTH_SHORT).show()
        }
    }
}