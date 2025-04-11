package uk.ac.tees.mad.expensetracker.data.local.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(entity: ExpenseEntity)

    @Query("SELECT * FROM expense_table")
    fun getExpenses(): Flow<List<ExpenseEntity>>
}