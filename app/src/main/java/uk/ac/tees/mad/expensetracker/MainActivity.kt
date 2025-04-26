package uk.ac.tees.mad.expensetracker

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import uk.ac.tees.mad.expensetracker.data.local.datastore.DataStoreManager
import uk.ac.tees.mad.expensetracker.navigation.MainNavigation
import uk.ac.tees.mad.expensetracker.ui.theme.ExpenseTrackerTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var dataStoreManager: DataStoreManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkMode by dataStoreManager.isDarkModeFlow.collectAsState(false)
            ExpenseTrackerTheme(isDarkMode) {
                MainNavigation()
            }
        }
    }
}