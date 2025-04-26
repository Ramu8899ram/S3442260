package uk.ac.tees.mad.expensetracker.screen.splash

import androidx.activity.compose.LocalActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import uk.ac.tees.mad.expensetracker.R
import uk.ac.tees.mad.expensetracker.util.Routes

@Composable
fun SplashScreen(navController: NavController,viewModel: SplashViewModel = hiltViewModel()) {
    var logoShrunk by remember { mutableStateOf(false) }
    var showProgressbar by remember { mutableStateOf(false) }

    val authSuccess by viewModel.authSuccess.collectAsState()
    val authError by viewModel.authError.collectAsState()
    val isFingerLock by viewModel.isFingerprintLock.collectAsState()
    val isLoaded by viewModel.isLoaded.collectAsState()
    val activity = LocalActivity.current as? FragmentActivity

    val logoSize by animateDpAsState(
        targetValue = if (logoShrunk) 40.dp else 120.dp,
        animationSpec = tween(durationMillis = 1000),
        label = "Logo Animation"
    )

    val animatedProgress by animateFloatAsState(
        targetValue = if (showProgressbar) 1f else 0f,
        animationSpec = tween(durationMillis = 1500),
        label = "Progress Animation"
    )

    val user = FirebaseAuth.getInstance().currentUser

    LaunchedEffect(isLoaded) {
        delay(500)
        logoShrunk = true
        delay(1000)
        showProgressbar = true
        delay(1500)
        if (isFingerLock) {
            activity?.let {
                viewModel.authenticate(it)
            }
        }else{
            if (isLoaded) {
                navController.navigate(if(user==null) Routes.AUTH_SCREEN else Routes.MAIN_SCREEN){
                    popUpTo(Routes.SPLASH_SCREEN){
                        inclusive = true
                    }
                }
            }
        }
    }

    LaunchedEffect(authSuccess, isLoaded) {
        if (authSuccess && isLoaded){
            navController.navigate(if(user==null) Routes.AUTH_SCREEN else Routes.MAIN_SCREEN){
                popUpTo(Routes.SPLASH_SCREEN){
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(authError) {
        if (authError){
            activity?.finish()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.app_logo),
                contentDescription = "app_logo",
                modifier = Modifier.size(logoSize)
            )

            AnimatedVisibility(
                showProgressbar
            ) {
                LinearProgressIndicator(
                    progress = { animatedProgress },
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .width(150.dp)
                        .height(8.dp),
                    color = Color(0xFF1eb4eb),
                    trackColor = Color.Gray,
                    strokeCap = StrokeCap.Round,
                )
            }
        }
    }
}