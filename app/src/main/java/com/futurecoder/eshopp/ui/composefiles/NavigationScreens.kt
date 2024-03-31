@file:OptIn(ExperimentalMaterial3Api::class)

package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.futurecoder.eshopp.R
import com.futurecoder.eshopp.utils.AddAddressDestination
import com.futurecoder.eshopp.utils.AddressDestination
import com.futurecoder.eshopp.utils.CartDestination
import com.futurecoder.eshopp.utils.DashboardDestination
import com.futurecoder.eshopp.utils.OnBoardingDestination
import com.futurecoder.eshopp.utils.ProductDetailDestination
import com.futurecoder.eshopp.utils.ProfileDestination
import com.futurecoder.eshopp.utils.SearchDestination
import com.futurecoder.eshopp.utils.SignupDestination
import com.futurecoder.eshopp.utils.SplashDestination
import com.futurecoder.eshopp.utils.WishlistDestination

@Composable
fun NavigationScreens(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Surface {
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
                navController.navigate(SignupDestination.ROUTE)
            }
        }
        NavHost(
            navController = navController,
            startDestination = SplashDestination.ROUTE
        ) {
            composable(SplashDestination.ROUTE) {
                SplashScreen(splashIcon = R.drawable.splash_logo) {
                    navController.navigate(OnBoardingDestination.ROUTE) {
                        popUpTo(SplashDestination.ROUTE) {
                            inclusive = true
                        }
                    }
                }
            }
            composable(OnBoardingDestination.ROUTE) {
                OnboardingScreen {
                    navController.navigate(DashboardDestination.ROUTE) {
                        popUpTo(OnBoardingDestination.ROUTE) {
                            inclusive = true
                        }
                    }
                }
            }
            composable(DashboardDestination.ROUTE) {
                DashboardScreen(onProfileIconClicked = { bottomSheet, isCurrentUser ->
                    if (isCurrentUser) {
                        navController.navigate(ProfileDestination.ROUTE)
                    } else {
                        showBottomSheet.value = bottomSheet
                    }
                }, openDetailScreen = { destinationRoute, productId ->
                    navController.navigate("$destinationRoute?productId=$productId")
                }) {
                    navController.navigate(SearchDestination.ROUTE)
                }
            }
            composable(SearchDestination.ROUTE) {
                SearchScreen()
            }
            composable(SignupDestination.ROUTE) {
                SignUpComposable()
            }
            composable(ProfileDestination.ROUTE) {
                ProfileComposable(
                    onAddressCardClick = {
                        navController.navigate(AddressDestination.ROUTE)
                    }
                ) {
                    navController.navigate(DashboardDestination.ROUTE) {
                        popUpTo(DashboardDestination.ROUTE) {
                            inclusive = false
                        }
                    }
                }
            }
            composable(AddressDestination.ROUTE) {
                AddressScreen(
                    onEditClick = { addressId ->
                        /**
                         * here addressId is optional argument(we can pass it or not,
                         * if we are not passing any argument then it will not throw any exception)
                         * but for that when defining it in composable function we need to
                         * set defaultValue for it or make it nullable as we have done in AddAddressScreen composable()
                         */
                        navController.navigate("${AddAddressDestination.ROUTE}?addressId=$addressId")
                    },
                    onAddManuallyClick = {
                        navController.navigate(AddAddressDestination.ROUTE)
                    }
                )
            }
            composable(
                "${AddAddressDestination.ROUTE}?addressId=${AddAddressDestination.ARGS}",
                arguments = listOf(navArgument("addressId") {
                    // default value is passed so that if we do not pass any argument during navigation then it will not throw any exception
                    defaultValue = -1L
                    type = NavType.LongType
                })
            ) { backStackEntry ->
                AddAddressScreen {
                    navController.navigate(AddressDestination.ROUTE) {
                        popUpTo(AddressDestination.ROUTE) {
                            inclusive = true
                        }
                    }
                }
            }
            composable(
                "${ProductDetailDestination.ROUTE}?productId=${ProductDetailDestination.ARGS}",
                arguments = listOf(navArgument("productId") {
                    defaultValue = -1L
                    type = NavType.LongType
                })
            ) {
                ProductDetailScreen()
            }
            composable(WishlistDestination.ROUTE) {
                WishlistScreen {
                    navController.navigate(it)
                }
            }
            composable(CartDestination.ROUTE) {
                CartScreen {
                    navController.navigate(it)
                }
            }
        }
    }
}