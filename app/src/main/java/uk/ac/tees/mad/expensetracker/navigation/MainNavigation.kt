package uk.ac.tees.mad.expensetracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.expensetracker.screen.auth.AuthScreen
import uk.ac.tees.mad.expensetracker.screen.splash.SplashScreen
import uk.ac.tees.mad.expensetracker.util.Routes

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Routes.SPLASH_SCREEN) {

        composable(Routes.SPLASH_SCREEN) {
            SplashScreen(navController)
        }

        composable(Routes.AUTH_SCREEN) {
            AuthScreen(navController)
        }
    }
}