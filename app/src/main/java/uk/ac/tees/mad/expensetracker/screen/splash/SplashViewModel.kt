package uk.ac.tees.mad.expensetracker.screen.splash

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import uk.ac.tees.mad.expensetracker.data.local.datastore.DataStoreManager
import uk.ac.tees.mad.expensetracker.data.local.remote.ExchangeApiService
import uk.ac.tees.mad.expensetracker.data.local.roomdb.ExpenseEntity
import uk.ac.tees.mad.expensetracker.data.repository.Repository
import uk.ac.tees.mad.expensetracker.util.Constants
import uk.ac.tees.mad.expensetracker.util.Constants.APIKEY
import uk.ac.tees.mad.expensetracker.util.Utils
import java.util.concurrent.Executor
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    exchangeApiService: ExchangeApiService,
    private val repository: Repository,
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val executor: Executor
):ViewModel() {
    private val _authSuccess = MutableStateFlow(false)
    val authSuccess:StateFlow<Boolean> get() = _authSuccess

    private val _authFailed = MutableStateFlow(false)
    val authFailed:StateFlow<Boolean> get() = _authFailed

    private val _isLoaded = MutableStateFlow(false)
    val isLoaded:StateFlow<Boolean> get() = _isLoaded

    private val _authError = MutableStateFlow(false)
    val authError:StateFlow<Boolean> get() = _authError

    private val _isFingerprintLock = MutableStateFlow(false)
    val isFingerprintLock:StateFlow<Boolean> get() = _isFingerprintLock

    private val userId = auth.currentUser?.uid?:""
    init {
        viewModelScope.launch {
            try {
                val response = exchangeApiService.getExchangeRate(APIKEY, "USD")
                dataStoreManager.saveExchangeRate(Utils.objectToJson(response))
                _isLoaded.value = true
            } catch (_: Exception) {
                _isLoaded.value = true
            }
        }
        viewModelScope.launch {
            _isFingerprintLock.value = dataStoreManager.isFingerprintLockFlow.first()
        }

        viewModelScope.launch {
            val data = repository.getExpenses().first()
            if (data.isEmpty() && !userId.isEmpty()){
                db.collection(Constants.USERS)
                    .document(userId)
                    .collection(Constants.EXPENSES)
                    .get().addOnSuccessListener { documents->
                        for (doc in documents){
                            val mExpense = doc.toObject(ExpenseEntity::class.java)
                            val entity = ExpenseEntity(
                                amount = mExpense.amount,
                                currency = mExpense.currency,
                                paymentMode = mExpense.paymentMode,
                                category = mExpense.category,
                                note =mExpense.note,
                                receiptImage = mExpense.receiptImage,
                                time = mExpense.time
                            )

                            viewModelScope.launch {
                                repository.insertExpense(entity)
                            }
                        }
                    }
            }
        }
    }

    fun authenticate(activity: FragmentActivity) {
        val biometricPrompt = BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                _authSuccess.value = true
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                _authFailed.value = true
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                _authError.value = true
            }
        })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Login using fingerprint")
            .setAllowedAuthenticators(
                BiometricManager.Authenticators.BIOMETRIC_STRONG or
                BiometricManager.Authenticators.DEVICE_CREDENTIAL

            )
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}