package uk.ac.tees.mad.expensetracker.model

import androidx.compose.ui.graphics.Color

data class CategoryModel(
    val category: String,
    val value: Int,
    val icon: Int,
    val color: Color
)
