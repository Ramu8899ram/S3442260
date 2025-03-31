package uk.ac.tees.mad.smartexpensecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import uk.ac.tees.mad.smartexpensecalculator.navigation.MainNavigation
import uk.ac.tees.mad.smartexpensecalculator.ui.theme.SmartExpenseCalculatorTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartExpenseCalculatorTheme {
                MainNavigation()
            }
        }
    }
}