package uk.ac.tees.mad.expensetracker.data.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.expensetracker.data.local.roomdb.ExpenseDao
import uk.ac.tees.mad.expensetracker.data.local.roomdb.ExpenseEntity
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dao: ExpenseDao
): Repository {
    override suspend fun insertExpense(entity: ExpenseEntity) {
        dao.insertExpense(entity)
    }

    override fun getExpenses(): Flow<List<ExpenseEntity>> {
        return dao.getExpenses()
    }
}