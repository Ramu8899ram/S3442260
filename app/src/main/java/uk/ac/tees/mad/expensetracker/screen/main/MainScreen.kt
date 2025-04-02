package uk.ac.tees.mad.expensetracker.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import uk.ac.tees.mad.expensetracker.component.DashboardTopAppbar
import uk.ac.tees.mad.expensetracker.screen.dashboard.DashboardScreen

@Composable
fun MainScreen(navController: NavController) {
    val selectedScreen = rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            if (selectedScreen.intValue == 0) {
                DashboardTopAppbar()
            }
        },
        bottomBar = {
            MainBottomNavigationBar(onSelect = {
                selectedScreen.intValue = it
            })
        },
        floatingActionButton = {
            if (selectedScreen.intValue == 0) {
                FloatingActionButton({}) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add"
                    )
                }
            }
        }
    ) { paddingValues ->
        val modifier = Modifier.padding(paddingValues)
        when (selectedScreen.intValue) {
            0 -> DashboardScreen(modifier)
        }
    }
}