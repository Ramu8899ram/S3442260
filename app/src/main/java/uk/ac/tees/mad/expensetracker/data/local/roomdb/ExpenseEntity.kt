package uk.ac.tees.mad.expensetracker.data.local.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_table")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val amount: Double=0.0,
    val currency: Int=0,
    val paymentMode: Int=0,
    val category: Int=0,
    val note: String="",
    val receiptImage: String="",
    val time: Long = System.currentTimeMillis()
)
