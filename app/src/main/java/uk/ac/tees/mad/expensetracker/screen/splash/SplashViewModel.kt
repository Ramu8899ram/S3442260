package uk.ac.tees.mad.expensetracker.screen.splash

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import uk.ac.tees.mad.expensetracker.data.local.datastore.DataStoreManager
import uk.ac.tees.mad.expensetracker.data.local.remote.ExchangeApiService
import uk.ac.tees.mad.expensetracker.util.Constants
import uk.ac.tees.mad.expensetracker.util.Utils
import java.util.concurrent.Executor
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    exchangeApiService: ExchangeApiService,
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

    val apiKey ="18055e06d1a4298f7e678e99"
    init {
        viewModelScope.launch {
            try {
                val response = exchangeApiService.getExchangeRate(apiKey, "USD")
                dataStoreManager.saveExchangeRate(Utils.objectToJson(response))
                _isLoaded.value = true
            } catch (e: Exception) {
                _isLoaded.value = true
            }
        }
        viewModelScope.launch {
            _isFingerprintLock.value = dataStoreManager.isFingerprintLockFlow.first()
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