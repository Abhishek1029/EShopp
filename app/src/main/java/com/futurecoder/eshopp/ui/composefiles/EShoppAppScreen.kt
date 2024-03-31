package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.futurecoder.eshopp.utils.EShoppConstants.CART
import com.futurecoder.eshopp.utils.EShoppConstants.DASHBOARD
import com.futurecoder.eshopp.utils.EShoppConstants.WISHLIST
import com.futurecoder.eshopp.utils.EShoppHelper.getNavBarItems

@ExperimentalMaterial3Api
@Composable
fun EShoppAppScreen(
) {
    val navController = rememberNavController()
    val bottomNavigationList = getNavBarItems()
    val navBarState = rememberSaveable {
        mutableStateOf(true)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    when (navBackStackEntry?.destination?.route) {
        DASHBOARD -> navBarState.value = true
        WISHLIST -> navBarState.value = true
        CART -> navBarState.value = true
        else -> navBarState.value = false
    }

    Scaffold(
        bottomBar = {
            EShoppNavigationBar(
                bottomNavigationList,
                navBackStackEntry,
                navBarState
            ) { destination ->
                navController.navigate(destination){
                    launchSingleTop = true
                }
            }
        }
    ) { innerPadding ->
        NavigationScreens(
            Modifier.padding(innerPadding),
            navController
        )
    }
}