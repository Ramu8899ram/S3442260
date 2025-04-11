package uk.ac.tees.mad.expensetracker.data.local.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ExpenseEntity::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase: RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    companion object{

        @Volatile
        private var INSTANCE: ExpenseDatabase? = null

        fun getDatabase(context: Context): ExpenseDatabase {
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseDatabase::class.java,
                    "expense2_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}