package uk.ac.tees.mad.smartexpensecalculator.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.smartexpensecalculator.screen.SplashScreen
import uk.ac.tees.mad.smartexpensecalculator.util.Routes

@Composable
fun MainNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController, startDestination = Routes.SPLASH_SCREEN){

        composable(Routes.SPLASH_SCREEN) {
            SplashScreen()
        }
    }
}