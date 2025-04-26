package uk.ac.tees.mad.expensetracker.data.local.datastore


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStoreManager @Inject constructor(private val dataStore:DataStore<Preferences>) {

    companion object{
        val IS_DARK_MODE_KEY = booleanPreferencesKey("is_dark_mode")
        val IS_FINGERPRINT_LOCK_KEY = booleanPreferencesKey("is_fingerprint_lock")
        val SELECTED_CURRENCY_KEY = intPreferencesKey("selected_currency")
        val EXCHANGE_RATE_KEY = stringPreferencesKey("exchange_rate_key")
    }

    suspend fun saveDarkModeStatus(isDarkMode:Boolean){
        dataStore.edit { preferences->
            preferences[IS_DARK_MODE_KEY] =isDarkMode
        }
    }

    suspend fun saveFingerprintLockStatus(isFingerLock:Boolean){
        dataStore.edit { preferences->
            preferences[IS_FINGERPRINT_LOCK_KEY] = isFingerLock
        }
    }

    suspend fun saveSelectedCurrency(newCurrency: Int){
        dataStore.edit { preferences->
            preferences[SELECTED_CURRENCY_KEY] = newCurrency
        }
    }

    suspend fun saveExchangeRate(exchangeRate: String){
        dataStore.edit { pref->
            pref[EXCHANGE_RATE_KEY] = exchangeRate
        }
    }

    val isDarkModeFlow:Flow<Boolean> = dataStore.data.map {
        preferences->
        preferences[IS_DARK_MODE_KEY] == true
    }

    val isFingerprintLockFlow:Flow<Boolean> = dataStore.data.map {
        preferences ->
        preferences[IS_FINGERPRINT_LOCK_KEY] == true
    }

    val selectedCurrencyFlow:Flow<Int> = dataStore.data.map {
        preferences-> preferences[SELECTED_CURRENCY_KEY]?:0
    }

    val exchangeRateFow: Flow<String> = dataStore.data.map {
        pref-> pref[EXCHANGE_RATE_KEY]?:""
    }
}