package uk.ac.tees.mad.expensetracker.screen.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uk.ac.tees.mad.expensetracker.data.local.remote.ExchangeApiService
import uk.ac.tees.mad.expensetracker.util.Constants.APIKEY
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val exchangeApiService: ExchangeApiService
): ViewModel() {
    init {
        viewModelScope.launch {
            Log.d("Exchange API Response", "INITIALIZED")
            val response = exchangeApiService.getExchangeRate(APIKEY, "USD")
            Log.d("Exchange API Response", response.conversion_rates.toString())
        }
    }
}