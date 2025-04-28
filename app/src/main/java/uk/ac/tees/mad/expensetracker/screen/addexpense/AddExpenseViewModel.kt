package uk.ac.tees.mad.expensetracker.screen.addexpense

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.expensetracker.data.local.datastore.DataStoreManager
import uk.ac.tees.mad.expensetracker.data.local.roomdb.ExpenseEntity
import uk.ac.tees.mad.expensetracker.data.repository.Repository
import uk.ac.tees.mad.expensetracker.util.Constants
import uk.ac.tees.mad.expensetracker.util.Utils
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val repository: Repository,
    private val dataStoreManager: DataStoreManager,
    private val fireStore: FirebaseFirestore,
    private val auth: FirebaseAuth
): ViewModel() {
    val userId = auth.currentUser?.uid?:""
    private val _selectedCurrency = MutableStateFlow(0)
    val selectedCurrency:StateFlow<Int> get() = _selectedCurrency
    init {
        viewModelScope.launch {
            dataStoreManager.selectedCurrencyFlow.collect{_selectedCurrency.value = it}
        }
    }
    fun addExpense(
        amount: String, currency: Int, pMode: Int,
        category: Int, note: String, image: Bitmap?,
        context: Context, onComplete:()-> Unit
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
            receiptImage = if (image!=null) Utils.bitmapToBase64(image)?:"" else ""
        )
        fireStore.collection(Constants.USERS)
            .document(userId)
            .collection(Constants.EXPENSES)
            .add(entity)
            .addOnSuccessListener{
                viewModelScope.launch {
                    repository.insertExpense(entity)
                    Toast.makeText(context, "Expense add successfully", Toast.LENGTH_SHORT).show()
                    onComplete()
                }
            }
    }

    fun onCurrencyChange(currency:Int){
        _selectedCurrency.value = currency
    }
}