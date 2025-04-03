package uk.ac.tees.mad.expensetracker.screen.settings

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.expensetracker.data.local.datastore.DataStoreManager
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val auth: FirebaseAuth
):ViewModel() {

    private val _name = MutableStateFlow("Name")
    val name: StateFlow<String> get() = _name
    private val _email = MutableStateFlow("Email")
    val email: StateFlow<String> get() = _email

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode:StateFlow<Boolean> get() = _isDarkMode

    private val _isFingerprintLock = MutableStateFlow(false)
    val isFingerprintLock:StateFlow<Boolean> get() = _isFingerprintLock

    private val _selectedCurrency = MutableStateFlow("usd")
    val selectedCurrency:StateFlow<String> get() = _selectedCurrency

    init {
        _name.value = auth.currentUser?.displayName.toString()
        _email.value = auth.currentUser?.email.toString()

        viewModelScope.launch {
            dataStoreManager.isDarkModeFlow.collect{_isDarkMode.value = it}
        }
        viewModelScope.launch {
            dataStoreManager.isFingerprintLockFlow.collect{_isFingerprintLock.value = it}
        }
        viewModelScope.launch {
            dataStoreManager.selectedCurrencyFlow.collect{_selectedCurrency.value = it}
        }
    }

    fun updateProfile(newName:String, context: Context){
        _name.value = newName
        viewModelScope.launch {
            val profileUpdate = UserProfileChangeRequest.Builder()
                .setDisplayName(_name.value)
                .build()
            auth.currentUser?.updateProfile(profileUpdate)
            Toast.makeText(context, "Name changed successfully", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveDarkModeStatus(value:Boolean){
        viewModelScope.launch {
            dataStoreManager.saveDarkModeStatus(value)
            _isDarkMode.value = value
        }
    }

    fun saveFingerLockStatus(value:Boolean){
        viewModelScope.launch {
            dataStoreManager.saveFingerprintLockStatus(value)
            _isFingerprintLock.value = value
        }
    }

    fun saveSelectedCurrency(newCurrency:String){
        viewModelScope.launch {
            dataStoreManager.saveSelectedCurrency(newCurrency)
            _selectedCurrency.value = newCurrency
        }
    }

    fun logOut(){
        auth.signOut()
    }
}