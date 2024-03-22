package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.futurecoder.eshopp.R
import com.futurecoder.eshopp.utils.AddAddressDestination
import com.futurecoder.eshopp.utils.AddressDestination
import com.futurecoder.eshopp.utils.DashboardDestination
import com.futurecoder.eshopp.utils.ProfileDestination
import com.futurecoder.eshopp.utils.SearchDestination
import com.futurecoder.eshopp.utils.SignupDestination
import com.futurecoder.eshopp.utils.SplashDestination

@ExperimentalMaterial3Api
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
                DashboardScreen(onProfileIconClicked = { bottomSheet, isCurrentUser ->
                    if (isCurrentUser) {
                        navController.navigate(ProfileDestination.route)
                    } else {
                        showBottomSheet.value = bottomSheet
                    }
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
            composable(ProfileDestination.route) {
                ProfileComposable(
                    onAddressCardClick = {
                        navController.navigate(AddressDestination.route)
                    }
                ) {
                    navController.navigate(DashboardDestination.route) {
                        popUpTo(DashboardDestination.route) {
                            inclusive = false
                        }
                    }
                }
            }
            composable(AddressDestination.route) {
                AddressScreen(
                    onEditClick = { addressId ->
                        /**
                         * here addressId is optional argument(we can pass it or not,
                         * if we are not passing any argument then it will not throw any exception)
                         * but for that when defining it in composable function we need to
                         * set defaultValue for it or make it nullable as we have done in AddAddressScreen composable()
                         */
                        navController.navigate("${AddAddressDestination.route}?addressId=$addressId")
                    },
                    onAddManuallyClick = {
                        navController.navigate(AddAddressDestination.route)
                    }
                )
            }
            composable(
                "${AddAddressDestination.route}?addressId=${AddAddressDestination.args}",
                arguments = listOf(navArgument("addressId") {
                    // default value is passed so that if we do not pass any argument during navigation then it will not throw any exception
                    defaultValue = -1L
                    type = NavType.LongType
                })
            ) { backStackEntry ->
                AddAddressScreen() {
                    navController.navigate(AddressDestination.route) {
                        popUpTo(AddressDestination.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}