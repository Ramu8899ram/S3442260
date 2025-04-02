package uk.ac.tees.mad.expensetracker.screen.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import uk.ac.tees.mad.expensetracker.R

@Composable
fun MainBottomNavigationBar(onSelect:(Int)-> Unit) {
    val selectedScreen = rememberSaveable { mutableIntStateOf(0) }
    NavigationBar {
        NavigationBarItem(
            selected = selectedScreen.intValue==0,
            onClick = {
                selectedScreen.intValue=0
                onSelect(selectedScreen.intValue)
                      },
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_dashboard_24),
                    contentDescription = "dashboard"
                )
            },
            label = {
                Text("Dashboard")
            }
        )
        NavigationBarItem(
            selected = selectedScreen.intValue==1,
            onClick = {
                selectedScreen.intValue=1
                onSelect(selectedScreen.intValue)
                      },
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_analytics_24),
                    contentDescription = "activity"
                )
            },
            label = {
                Text("Activity")
            }
        )
        NavigationBarItem(
            selected = selectedScreen.intValue==2,
            onClick = {
                selectedScreen.intValue=2
                onSelect(selectedScreen.intValue)
                      },
            icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "settings"
                )
            },
            label = {
                Text("Settings")
            }
        )
    }
}