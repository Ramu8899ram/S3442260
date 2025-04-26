package uk.ac.tees.mad.expensetracker.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.ac.tees.mad.expensetracker.data.local.remote.ExchangeApiService
import uk.ac.tees.mad.expensetracker.data.local.roomdb.ExpenseDao
import uk.ac.tees.mad.expensetracker.data.local.roomdb.ExpenseDatabase
import uk.ac.tees.mad.expensetracker.data.repository.Repository
import uk.ac.tees.mad.expensetracker.data.repository.RepositoryImpl
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "app_preferences")

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideExecutor(): Executor = Executors.newSingleThreadExecutor()

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFireStore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) = context.dataStore

    @Provides
    @Singleton
    fun provideExpenseDatabase(
        @ApplicationContext context: Context
    ): ExpenseDatabase{
        return ExpenseDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideExpenseDao(db: ExpenseDatabase): ExpenseDao{
        return db.expenseDao()
    }

    @Provides
    @Singleton
    fun provideRepository(dao: ExpenseDao): Repository{
        return RepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideExchangeApiService(): ExchangeApiService{
        return Retrofit.Builder()
            .baseUrl("https://v6.exchangerate-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExchangeApiService::class.java)
    }
}