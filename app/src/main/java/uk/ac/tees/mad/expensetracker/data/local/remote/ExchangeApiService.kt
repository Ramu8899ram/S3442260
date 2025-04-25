package uk.ac.tees.mad.expensetracker.data.local.remote

import retrofit2.http.GET
import retrofit2.http.Path
import uk.ac.tees.mad.expensetracker.model.CurrencyResponse

interface ExchangeApiService {
    @GET("v6/{apiKey}/latest/{currency}")
    suspend fun getExchangeRate(
        @Path("apiKey") apiKey: String,
        @Path("currency") currency: String
    ): CurrencyResponse
}