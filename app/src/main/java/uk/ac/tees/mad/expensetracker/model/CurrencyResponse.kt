package uk.ac.tees.mad.expensetracker.model

data class CurrencyResponse(
    val conversion_rates : Map<String, Double>
)