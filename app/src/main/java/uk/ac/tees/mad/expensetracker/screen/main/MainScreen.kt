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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import uk.ac.tees.mad.expensetracker.component.DashboardTopAppbar
import uk.ac.tees.mad.expensetracker.screen.dashboard.DashboardScreen
import uk.ac.tees.mad.expensetracker.screen.history.HistoryScreen
import uk.ac.tees.mad.expensetracker.screen.settings.SettingsScreen
import uk.ac.tees.mad.expensetracker.util.Routes

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
            else if(selectedScreen.intValue==1){
                Text(
                    "Activities",
                    fontSize = 26.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 30.dp, start = 16.dp)
                )
            }
            else{
                Text(
                    "Settings",
                    fontSize = 26.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 30.dp, start = 16.dp)
                )
            }
        },
        bottomBar = {
            MainBottomNavigationBar(onSelect = {
                selectedScreen.intValue = it
            })
        },
        floatingActionButton = {
            if (selectedScreen.intValue == 0) {
                FloatingActionButton({
                    navController.navigate(Routes.ADD_EXPENSE_SCREEN)
                }) {
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
            1 -> HistoryScreen(modifier)
            2 -> SettingsScreen(modifier, navController)
        }
    }
}