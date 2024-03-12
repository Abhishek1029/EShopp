package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.futurecoder.eshopp.R
import com.futurecoder.eshopp.utils.DashboardDestination
import com.futurecoder.eshopp.utils.SearchDestination
import com.futurecoder.eshopp.utils.SignupDestination
import com.futurecoder.eshopp.utils.SplashDestination
import com.futurecoder.eshopp.viewmodels.DashboardViewModel

@Composable
fun EShoppAppScreen(
    modifier: Modifier = Modifier
) {
    Surface {
        val navController = rememberNavController()
        val showBottomSheet = remember {
            mutableStateOf(false)
        }
        if (showBottomSheet.value) {
            DisplayBottomSheet(
                onDismissClicked = {
                    showBottomSheet.value = false
                }
            ) {
                showBottomSheet.value = false
                navController.navigate(SignupDestination.route)
            }
        }
        NavHost(
            navController = navController,
            startDestination = SplashDestination.route
        ) {
            composable(SplashDestination.route) {
                SplashScreen(splashIcon = R.drawable.splash_logo) {
                    navController.navigate(DashboardDestination.route) {
                        popUpTo(SplashDestination.route) {
                            inclusive = true
                        }
                    }
                }
            }
            composable(DashboardDestination.route) {
                DashboardScreen(onProfileIconClicked = {
                    showBottomSheet.value = it
                }) {
                    navController.navigate(SearchDestination.route)
                }
            }
            composable(SearchDestination.route) {
                SearchScreen()
            }
            composable(SignupDestination.route) {
                SignUpComposable()
            }
        }
    }
}