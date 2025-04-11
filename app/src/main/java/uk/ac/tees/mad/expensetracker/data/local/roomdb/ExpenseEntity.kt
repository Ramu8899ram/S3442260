package uk.ac.tees.mad.expensetracker.data.local.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_table")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val amount: Double,
    val currency: String,
    val paymentMode: Int,
    val category: Int,
    val note: String,
    val receiptImage: String,
    val time: Long = System.currentTimeMillis()
)
