package uk.ac.tees.mad.expensetracker.screen.main

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import uk.ac.tees.mad.expensetracker.component.DashboardTopAppbar
import uk.ac.tees.mad.expensetracker.screen.dashboard.DashboardScreen
import uk.ac.tees.mad.expensetracker.screen.settings.SettingsScreen

@Composable
fun MainScreen(navController: NavController) {
    val selectedScreen = rememberSaveable { mutableIntStateOf(0) }
    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
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
            2 -> SettingsScreen(modifier, navController)
        }
    }
}