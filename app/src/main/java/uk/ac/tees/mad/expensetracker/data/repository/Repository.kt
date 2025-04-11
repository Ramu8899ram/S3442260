package uk.ac.tees.mad.expensetracker.data.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.expensetracker.data.local.roomdb.ExpenseEntity

interface Repository {
    suspend fun insertExpense(entity: ExpenseEntity)
    fun getExpenses(): Flow<List<ExpenseEntity>>
}