package uk.ac.tees.mad.expensetracker.model

import androidx.compose.ui.graphics.Color

data class CategoryModel(
    val category: String,
    val expenses: Double,
    val percentage: Int,
    val paymentMode: String,
    val icon: Int,
    val color: Color
)
